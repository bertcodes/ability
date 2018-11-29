### 一、一致性Hash算法背景
一致性哈希算法在1997年由麻省理工学院的Karger等人在解决分布式Cache中提出来的，设计目标是为了解决因特网中的热点（Hot spot）问题，初衷和CARP十分类似。一致性
哈希修正了CARP使用的简单哈希算法带来的问题，使得DHT可以在P2P环境中真正得到应用。

但现在一致性hash算法在分布式系统中也得到了广泛的应用，研究过memcached缓存数据库的人都知道，memcached服务器本身不提供分布式cache的一致性，而是由客户端来提供
，具体在计算一致性hash时采用如下步骤：
* 1 首先求出memcached服务器（节点）的哈希值，并将其配置到0~2^32的圆（continuum）上。
* 2 然后采用同样的方法求出存储数据的键的哈希值，并映射到相同的圆上。
* 3 然后从数据映射到的位置开始顺时针查找，将数据保存到找到的第一个服务器上。如果超过2^32仍然找不到服务器，就会保存到第一台memcached服务器上。
![image](https://github.com/bertcodes/ability/blob/master/arithmetic/hash/image/hash-1th.png)
从上图的状态中添加一台memcached服务器。余数分布式算法由于保存键的服务器会发生巨大变化而影响缓存的命中率，但Consistent Hashing中，只有在园（continuum）上增加服务器的地点逆时针方向的第一台服务器上的键会受到影响，如下图所示：
![image](https://github.com/bertcodes/ability/blob/master/arithmetic/hash/image/hash-2th.png)

