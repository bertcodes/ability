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

上面那张图，现在某个客户端要加锁。如果该客户端面对的是一个redis cluster集群，他首先会根据hash节点选择一台机器。注意

只选择只选择一台机器。

接着发送一段lua脚本给redis：

if (redis.call('exists', KEYS[1]) == 0) then redis.call('hset', KEYS[1], ARGV[2], 1); 

redis.call('pexpire', KEYS[1], ARGV[1]); return nil; end; 

if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then redis.call('hincrby', KEYS[1], ARGV[2], 1); 

redis.call('pexpire', KEYS[1], ARGV[1]); return nil; end; return redis.call('pttl', KEYS[1]);




reference: id:shishan100
