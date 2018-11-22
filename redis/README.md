Redis分布式锁的实现原理：

一、使用

Redis分布式锁，一般就是用Redisson框架就好了，非常的简便易用。支持redis单实例、redis哨兵、redis cluster、redis master-slave等各种部署架构，

可以去看看Redisson的官网，看看如何在项目中引入Redisson的依赖，然后基于Redis实现分布式锁的加锁与释放锁。

RLock lock = redisson.getLock("lock");

// 最常见的使用方法

lock.lock();

lock.unlock();

二、底层原理

1)加锁机制
![image](https://github.com/bertcodes/ability/blob/master/redis/redissonLockFlowChart.png)
上面那张图，现在某个客户端要加锁。如果该客户端面对的是一个redis cluster集群，他首先会根据hash节点选择一台机器。注意

只选择只选择一台机器。

接着发送一段lua脚本给redis：

if (redis.call('exists', KEYS[1]) == 0) then redis.call('hset', KEYS[1], ARGV[2], 1); 

redis.call('pexpire', KEYS[1], ARGV[1]); return nil; end; 

if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then redis.call('hincrby', KEYS[1], ARGV[2], 1); 

redis.call('pexpire', KEYS[1], ARGV[1]); return nil; end; return redis.call('pttl', KEYS[1]);

参数：
1, lock, 30000, 271ef3b5-e120-406b-8cd2-e0770561f079:1

为什么要用lua脚本？

因为一大堆负责的业务逻辑可以封装到lua脚本中再发送给redis，保障这段复杂业务逻辑的原子性。

这段lua脚本是什么意思？

KEYS[1]代表的是你加锁的那个key，比如说：

RLock lock = redisson.getLock("lock");

这里你自己设置了加锁的那个锁key就是“lock”。

ARGV[1]代表的就是锁key的默认生存时间，默认30秒。

ARGV[2]代表的是加锁的客户端的ID，类似于下面这样：

271ef3b5-e120-406b-8cd2-e0770561f079:1

第一段if判断语句，就是用“exists myLock”命令判断一下，如果你要加锁的那个锁key不存在的话，你就进行加锁。

如何加锁？
用下面的命令：
hset myLock 
    271ef3b5-e120-406b-8cd2-e0770561f079:1 1

通过这个命令设置一个hash数据结构，这行命令执行后，redis会写入数据如下图：
![image](https://github.com/bertcodes/ability/blob/master/redis/lock_value.png)

上述就代表“271ef3b5-e120-406b-8cd2-e0770561f079:1”这个客户端对“lock”这个锁key完成了加锁。

接着会执行“pexpire lock 30000”命令，设置myLock这个锁key的生存时间是30秒。


reference: id:shishan100
