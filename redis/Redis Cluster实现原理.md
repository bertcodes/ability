
### 引言

　　在redis cluster出现之前,通常通过以下几种方式搭建redis集群:
* 1.客户端分片  
这种方案将分片工作放在业务程序端，程序代码根据预先设置的路由规则，直接对多个Redis实例进行分布式访问。这样的好处是，不依赖于第三方分布式中间件，实现方法和代码都自己掌控，可随时调整，不用担心踩到坑。
这实际上是一种静态分片技术。Redis实例的增减，都得手工调整分片程序。基于此分片机制的开源产品，现在仍不多见。
这种分片机制的性能比代理式更好（少了一个中间分发环节）。但缺点是升级麻烦，对研发人员的个人依赖性强——需要有较强的程序开发能力做后盾。如果主力程序员离职，可能新的负责人，会选择重写一遍。
所以，这种方式下，可运维性较差。出现故障，定位和解决都得研发和运维配合着解决，故障时间变长。
个数据集合，而整个数据集合的某个数据子集存储在哪个节点对于用户来说是透明的。Redis Cluster具有分布式系统的特点，也具有分布式系统如何实现高可用性与数据一致性的难点

* 2.代理分片  
这种方案，将分片工作交给专门的代理程序来做。代理程序接收到来自业务程序的数据请求，根据路由规则，将这些请求分发给正确的Redis实例并返回给业务程序。
这种机制下，一般会选用第三方代理程序（而不是自己研发），因为后端有多个Redis实例，所以这类程序又称为分布式中间件。
这样的好处是，业务程序不用关心后端Redis实例，运维起来也方便。虽然会因此带来些性能损耗，但对于Redis这种内存读写型应用，相对而言是能容忍的。
这是我们推荐的集群实现方案。像基于该机制的开源产品Twemproxy，便是其中代表之一，应用非常广泛。
### 认识Redis Cluster
　　Redis Cluster是由多个同时服务于一个数据集合的Redis实例组成的整体，对于用户来说，用户只关注这个数据集合，而整个数据集合的某个数据子集存储在哪个节点对于用户来说是透明的。Redis Cluster具有分布式系统的特点，也具有分布式系统如何实现高可用性与数据一致性的难点

![image](https://github.com/bertcodes/ability/blob/master/redis/image/redis_cluster_1th.png)

Redis Cluster特点如下：
* 1.所有的redis节点彼此互联(PING-PONG机制),内部使用二进制协议优化传输速度和带宽。
* 2.节点的fail是通过集群中超过半数的节点检测失效时才生效。
* 3.客户端与redis节点直连,不需要中间proxy层.客户端不需要连接集群所有节点,连接集群中任何一个可用节点即可。
* 4.redis-cluster把所有的物理节点映射到[0-16383]slot上（不一定是平均分配）,cluster 负责维护node<->slot<->value。
* 5.Redis集群预分好16384个桶，当需要在 Redis 集群中放置一个 key-value 时，根据 CRC16(key) mod 16384的值，决定将一个key放到哪个桶中。

### Redis Cluster搭建

集群中至少应该有奇数个节点，所以至少有三个节点，每个节点至少有一个备份节点，所以下面使用6节点（主节点、备份节点由redis-cluster集群确定）。  
1.进入到redis目录下，配置redis的配置文件redis.conf  
daemonize yes #后台启动  
port 7001 #修改端口号，从7001到7006  
cluster-enabled yes #开启cluster，去掉注释  
cluster-config-file nodes.conf  
cluster-node-timeout 15000  
appendonly yes  

2.将redis.conf 配置文件复制6份，分别修改对应的端口，6个文件如下：

redis_7001.conf,redis_7002.conf,redis_7003.conf,redis_7004.conf,redis_7006.conf  

3.安装redis-trib所需的 ruby脚本  

yum install ruby   
yum install rubygems   
gem install redis-xxx.gem  

4.启动redis服务  

./redis-server ../redis_7001.conf  
./redis-server ../redis_7002.conf   
./redis-server ../redis_7003.conf  
./redis-server ../redis_7004.conf  
./redis-server ../redis_7005.conf  
./redis-server ../redis_7006.conf  

5.创建redis-cluster

redis-trib.rb create --replicas 1 127.0.0.1:6310 127.0.0.1:6320 127.0.0.1:6330 127.0.0.1:6340 127.0.0.1:6350 127.0.0.1:6360

使用create命令 --replicas 1 参数表示为每个主节点创建一个从节点，其他参数是实例的地址集合。

6.redis集群的测试  
客户端连接集群redis-cli需要带上 -c ，redis-cli -c -p 端口号

./redis-cli -c -p 7001    
127.0.0.1:7001> set name andy    
-> Redirected to slot [5798] located at 127.0.0.1:7002    
OK    
127.0.0.1:7002> get name    
"andy"    
127.0.0.1:7002>  

根据redis-cluster的key值分配，name应该分配到节点7002[5461-10922]上，上面显示redis cluster自动从7001跳转到了7002节点。  
我们可以测试一下7006从节点获取name值,至此我们redis的集群就成功搭建了。  

### Redis Cluster实现原理  
Redis Cluster 的原理其实也很简单，用一张大图概括如下：
![image](https://github.com/bertcodes/ability/blob/master/redis/image/redis_cluster_2th.png)
* 1.对象保存到Redis之前先经过CRC16哈希到一个指定的Node上，例如Object4最终Hash到了Node1上。
* 2.每个Node被平均分配了一个Slot段，对应着0-16383，Slot不能重复也不能缺失，否则会导致对象重复存储或无法存储。
* 3.Node之间也互相监听，一旦有Node退出或者加入，会按照Slot为单位做数据的迁移。例如Node1如果掉线了，0-5640这些Slot将会平均分摊到剩余node上,由于剩余node本身维护的Slot还会在自己身上不会被重新分配，所以迁移过程中不会影响到5641-16383Slot段的使用。
###### 1. 槽（slot）概念
redis cluster引入槽的概念，一定要与一致性hash的槽区分！这里每一个槽映射一个数据集。

CRC16(key) & 16384

这里计算结果发送给redis cluster任意一个redis节点，这个redis节点发现他是属于自己管辖范围的，那就将它放进去；不属于他的槽范围的话，由于redis之间是相互通信的，这个节点是知道其他redis节点的槽的信息，那么会告诉他去那个redis节点去看看,这样就实现了服务端对于槽、节点、数据的管理。

当redis集群需要扩容的时候，由于每个节点维护的槽的范围是固定的，当有新加入的节点时，是不会干扰到其他节点的槽的，必须是以前的节点将使用槽的权利分配给你，并且将数据分配给你，这样，新的节点才会真正拥有这些槽和数据。这种实现还处于半自动状态，需要人工介入。主要的思想是：槽到集群节点的映射关系要改变，不变的是键到槽的映射关系。

Redis集群要保证16384个槽对应的node都正常工作，如果某个node发生故障，那它负责的slots也就失效，整个集群将不能工作。为了增加集群的可访问性，官方推荐的方案是将node配置成主从结构，即一个master主节点，挂n个slave从节点。这时，如果主节点失效，Redis Cluster会根据选举算法从slave节点中选择一个上升为主节点，整个集群继续对外提供服务。

###### 2. 位序列结构
某个Master是怎么知道某个槽自己是不是拥有呢？Master节点维护着一个16384/8字节的位序列，Master节点用bit来标识对于某个槽自己是否拥有。比如对于编号为1的槽，Master只要判断序列的第二位（索引从0开始）是不是为1即可。
![image](https://github.com/bertcodes/ability/blob/master/redis/image/redis_cluster_3th.png)

如上面的序列，表示当前Master拥有编号为1，101,102的槽。集群同时还维护着槽到集群节点的映射，是由长度为16384类型为节点的数组实现的，槽编号为数组的下标，数组内容为集群节点，这样就可以很快地通过槽编号找到负责这个槽的节点。位序列这个结构很精巧，即不浪费存储空间，操作起来又很便捷。

###### redis节点之间如何通信的？

![image](https://github.com/bertcodes/ability/blob/master/redis/image/redis_cluster_4th.png)
* 1.gossip协议：节点之间彼此不断通信交换信息，一段时间后所有节点都会知道集群完整的信息。
* 2.节点与节点之间通过二进制协议进行通信。
* 3.客户端和集群节点之间通信和通常一样，通过文本协议进行。
* 4.集群节点不会代理查询。

###### 3. slot 数据迁移

这里6382为新加入的节点，一开始是没有槽的，所以进行slot的迁移。    
![image](https://github.com/bertcodes/ability/blob/master/redis/image/redis_cluster_5th.png)
迁移数据的流程图：  
![image](https://github.com/bertcodes/ability/blob/master/redis/image/redis_cluster_6th.png)
槽迁移的过程中有一个不稳定状态，这个不稳定状态会有一些规则，这些规则定义客户端的行为，从而使得Redis Cluster不必宕机的情况下可以执行槽的迁移。    

* MIGRATING状态
预备迁移槽的时候槽的状态首先会变为MIGRATING状态，这种状态的槽会实际产生什么影响呢?当客户端请求的某个Key所属的槽处于MIGRATING状态的时候，影响有下面几条：

  1.如果Key存在则成功处理  
  2.如果Key不存在，则返回客户端ASK，仅当这次请求会转向另一个节点，并不会刷新客户端中node的映射关系，也就是说下次该客户端请求该Key的时候，还会选择MasterA节点如果Key包含多个命令，如果都存在则成功处理，如果都不存在，则返回客户端ASK，如果一部分存在，则返回客户端TRYAGAIN，通知客户端稍后重试，这样当所有的Key都迁移完毕的时候客户端重试请求的时候回得到ASK，然后经过一次重定向就可以获取这批键    
* IMPORTING状态  
  槽从MasterA节点迁移到MasterB节点的时候，槽的状态会首先变为IMPORTING。IMPORTING状态的槽对客户端的行为有下面一些影响：

正常命令会被MOVED重定向，如果是ASKING命令则命令会被执行，从而Key没有在老的节点已经被迁移到新的节点的情况可以被顺利处理；如果Key不存在则新建；没有ASKING的请求和正常请求一样被MOVED，这保证客户端node映射关系出错的情况下不会发生写错；

### 4. 客户端路由

* moved重定向
![image](https://github.com/bertcodes/ability/blob/master/redis/image/redis_cluster_7th.png)  
其中，槽直接命中的话，就直接返回槽编号：
![image](https://github.com/bertcodes/ability/blob/master/redis/image/redis_cluster_8th.png)  
槽不命中，返回带提示信息的异常，客户端需要重新发送一条命令：   
![image](https://github.com/bertcodes/ability/blob/master/redis/image/redis_cluster_9th.png)  
###### ask重定向

在扩容缩容的时候，由于需要遍历这个节点上的所有的key然后进行迁移，是比较慢的，对客户端是一个挑战。因为假设一个场景，客户端访问某个key，节点告诉客户端这个key在源节点，当我们再去源节点访问的时候，却发现key已经迁移到目标节点.

###### moved重定向和ask重定向对比

* 1.两者都是客户端单重定向
* 2.moved：槽已经确定转移
* 3.ask:槽还在迁移中
问题：如果节点众多，那么让客户端随机访问节点，那么直接命中的概率只有百分之一，还有就是发生ask异常时（即节点正在迁移时）客户端如何还能高效运转？
总结一句话就是redis cluster的客户端的实现会更复杂。
























from: https://mp.weixin.qq.com/s/-AhrpD2JfPxAtIhyfSdYOA
