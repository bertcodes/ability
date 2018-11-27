# Zookeeper架构及FastLeaderElection机制

## 一、zookeeper是什么
Zookeeper是一个分布式协调服务，可用于服务发现，分布式锁，分布式领导选举，配置管理等。

这一切的基础，都是Zookeeper提供了一个类似Linux文件系统的树形结构（可认为是轻量级的内存文件系统，但只适合存少量信息，完全不适合存储
大量文件或者大文件），同时提供了对于每个节点的监控与通知机制。

既然是一个文件系统，就不得不提Zookeeper是如何保证数据一致性。本文将介绍Zookeeper如何保证数据一致性，如何进行领导选举，以及数据监控/通知机制的语义保证。

## 二、Zookeeper架构
### 2.1 角色
Zookeeper集群是一个基于主从复制的高可用集群，每个服务器承担如下三种角色中的一种
* <b>Leader</b> 一个Zookeeper集群同一时间只会有一个实际工作的Leader，它会发起并维护与各Follwer及Observer间的心跳。所有的写操作必须要通过Leader完成再由Leader将写操作广播给其它服务器。
* <b>Follower</b> 一个Zookeeper集群可能同时存在多个Follower，它会响应Leader的心跳。Follower可直接处理并返回客户端的读请求，同时会将写请求转发给Leader处理，并且负责在Leader处理写请求时对请求进行投票。
* <b>Observer</b> 角色与Follower类似，但是无投票权。
![image](https://github.com/bertcodes/ability/blob/master/zookeeper/zk-1td.png)
### 2.2 原子广播（ZAB）
为了保证写操作的一致性，Zookeeper专门设计了一种名为原子广播（ZAB）的支持奔溃恢复的一致性协议。基于该协议，Zookeeper实现了一种主从模式的系统架构

来保持集群中各个副本之间的数据一致性。

根据ZAB协议，所有写操作必须通过Leader完成，Leader写入本地日志后再复制到所有的Follower节点。

一旦Leader节点无法工作，ZAB协议能够自动从Follower节点中重新选出一个合适的替代者，即新的Leader，该过程即为领导选举。该领导选举过程，是ZAB协议中最为

重要和复杂的过程。

### 2.3 写操作
### 1）写Leader
通过Leader进行写操作流程如下图所示
![image](https://github.com/bertcodes/ability/blob/master/zookeeper/zk-2td.png)

由上图可见，通过Leader进行写操作，主要分为五步：

客户端向Leader发起写请求
Leader将写请求以Proposal的形式发给所有Follower并等待ACK
Follower收到Leader的Proposal后返回ACK
Leader得到过半数的ACK（Leader对自己默认有一个ACK）后向所有的Follower和Observer发送Commmit
Leader将处理结果返回给客户端
这里要注意

Leader并不需要得到Observer的ACK，即Observer无投票权
Leader不需要得到所有Follower的ACK，只要收到过半的ACK即可，同时Leader本身对自己有一个ACK。上图中有4个Follower，只需其中两个返回ACK即可，因为(2+1) / (4+1) > 1/2
Observer虽然无投票权，但仍须同步Leader的数据从而在处理读请求时可以返回尽可能新的数据
### 2）写Follower/Observer
通过Follower/Observer进行写操作流程如下图所示：
![image](https://github.com/bertcodes/ability/blob/master/zookeeper/zk-3td.png)
从上图可见

Follower/Observer均可接受写请求，但不能直接处理，而需要将写请求转发给Leader处理
除了多了一步请求转发，其它流程与直接写Leader无任何区别
### 2.4 读操作
Leader/Follower/Observer都可直接处理读请求，从本地内存中读取数据并返回给客户端即可。
![image](https://github.com/bertcodes/ability/blob/master/zookeeper/zk-4td.png)
由于处理读请求不需要服务器之间的交互，Follower/Observer越多，整体可处理的读请求量越大，也即读性能越好。
## 三、FastLeaderElection原理
### 3.1 术语介绍
* <b>1) myid</b>
每个Zookeeper服务器，都需要在数据文件夹下创建一个名为myid的文件，该文件包含整个Zookeeper集群唯一的ID（整数）。例如某Zookeeper集群包含三台服务器，
hostname分别为zoo1、zoo2和zoo3，其myid分别为1、2和3，则在配置文件中其ID与hostname必须一一对应，如下所示。在该配置文件中，server.后面的数据即为
myid

  server.1=zoo1:2888:3888

  server.2=zoo2:2888:3888

  server.3=zoo3:2888:3888
* <b>2) zxid</b>
类似于RDBMS中的事务ID，用于标识一次更新操作的Proposal ID。为了保证顺序性，该zkid必须单调递增。因此Zookeeper使用一个64位的数来表示，高32位是Leader
的epoch，从1开始，每次选出新的Leader，epoch加一。低32位为该epoch内的序号，每次epoch变化，都将低32位的序号重置。这样保证了zkid的全局递增性。
### 3.2 支持的领导选举算法
可通过electionAlg配置项设置Zookeeper用于领导选举的算法。

到3.4.10版本为止，可选项有

0 基于UDP的LeaderElection

1 基于UDP的FastLeaderElection

2 基于UDP和认证的FastLeaderElection

3 基于TCP的FastLeaderElection

在3.4.10版本中，默认值为3，也即基于TCP的FastLeaderElection。另外三种算法已经被弃用，并且有计划在之后的版本中将它们彻底删除而不再支持。

#### FastLeaderElection
FastLeaderElection选举算法是标准的Fast Paxos算法实现，可解决LeaderElection选举算法收敛速度慢的问题。
#### 服务器状态
* <b>LOOKING </b> 不确定Leader状态。该状态下的服务器认为当前集群中没有Leader，会发起Leader选举
* <b>FOLLOWING  </b> 跟随者状态。表明当前服务器角色是Follower，并且它知道Leader是谁
* <b>LEADING  </b> 领导者状态。表明当前服务器角色是Leader，它会维护与Follower间的心跳
* <b>OBSERVING  </b> 观察者状态。表明当前服务器角色是Observer，与Folower唯一的不同在于不参与选举，也不参与集群写操作时的投票
#### 选票数据结构
每个服务器在进行领导选举时，会发送如下关键信息

* <b>logicClock  </b> 每个服务器会维护一个自增的整数，名为logicClock，它表示这是该服务器发起的第多少轮投票
* <b>state  </b> 当前服务器的状态
* <b>self_id  </b>d 当前服务器的myid
* <b>vote_id   </b> 当前服务器上所保存的数据的最大zxid
* <b>vote_zxid  </b> 被推举的服务器的myid
#### 投票流程
###### 自增选举轮次
Zookeeper规定所有有效的投票都必须在同一轮次中。每个服务器在开始新一轮投票时，会先对自己维护的logicClock进行自增操作。
###### 初始化选票
每个服务器在广播自己的选票前，会将自己的投票箱清空。该投票箱记录了所收到的选票。例：服务器2投票给服务器3，服务器3投票给服务器1，则服务器1的投票箱为(2, 3), (3, 1), (1, 1)。票箱中只会记录每一投票者的最后一票，如投票者更新自己的选票，则其它服务器收到该新选票后会在自己票箱中更新该服务器的选票。
###### 自发送初始化选票
每个服务器最开始都是通过广播把票投给自己。
###### 接收外部投票
服务器会尝试从其它服务器获取投票，并记入自己的投票箱内。如果无法获取任何外部投票，则会确认自己是否与集群中其它服务器保持着有效连接。如果是，则再次发送自己的投票；如果否，则马上与之建立连接。
###### 判断选举轮次
收到外部投票后，首先会根据投票信息中所包含的logicClock来进行不同处理

* 外部投票的logicClock大于自己的logicClock。说明该服务器的选举轮次落后于其它服务器的选举轮次，立即清空自己的投票箱并将自己的logicClock更新为收到的logicClock，然后再对比自己之前的投票与收到的投票以确定是否需要变更自己的投票，最终再次将自己的投票广播出去。

* 外部投票的logicClock小于自己的logicClock。当前服务器直接忽略该投票，继续处理下一个投票。

* 外部投票的logickClock与自己的相等。当时进行选票PK。
 
###### 选票PK
选票PK是基于(self_id, self_zxid)与(vote_id, vote_zxid)的对比
*  外部投票的logicClock大于自己的logicClock，则将自己的logicClock及自己的选票的logicClock变更为收到的logicClock
* 若logicClock一致，则对比二者的vote_zxid，若外部投票的vote_zxid比较大，则将自己的票中的vote_zxid与vote_myid更新为收到的票中的vote_zxid与vote_myid并广播出去，另外将收到的票及自己更新后的票放入自己的票箱。如果票箱内已存在(self_myid, self_zxid)相同的选票，则直接覆盖
* 若二者vote_zxid一致，则比较二者的vote_myid，若外部投票的vote_myid比较大，则将自己的票中的vote_myid更新为收到的票中的vote_myid并广播出去，另外将收到的票及自己更新后的票放入自己的票箱
###### 统计选票
如果已经确定有过半服务器认可了自己的投票（可能是更新后的投票），则终止投票。否则继续接收其它服务器的投票。
###### 更新服务器状态
投票终止后，服务器开始更新自身状态。若过半的票投给了自己，则将自己的服务器状态更新为LEADING，否则将自己的状态更新为FOLLOWING
## 四、几种领导选举场景
### 4.1 集群启动领导选举
###### 初始投票给自己
集群刚启动时，所有服务器的logicClock都为1，zxid都为0。

各服务器初始化后，都投票给自己，并将自己的一票存入自己的票箱，如下图所示
![image](https://github.com/bertcodes/ability/blob/master/zookeeper/zk-5th.png)









