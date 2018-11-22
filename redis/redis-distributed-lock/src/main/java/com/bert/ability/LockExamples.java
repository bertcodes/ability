package com.bert.ability;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * Copyright (C): Daemon©版权所有
 * FileName: LockExamples
 *
 * @author caobo
 * @create 2018-11-22 16:20
 * @since 1.0.0
 * Description:
 */
public class LockExamples {
    public static void main(String[] args) throws InterruptedException {
        // connects to 127.0.0.1:6379 by default
        RedissonClient redisson = Redisson.create();

        RLock lock = redisson.getLock("lock");
        lock.lock();
//        lock.unlock();

//        lock.lock(2, TimeUnit.SECONDS);
//
//        Thread t = new Thread() {
//            public void run() {
//                RLock lock1 = redisson.getLock("lock");
//                lock1.lock();
//                lock1.unlock();
//            };
//        };
//
//        t.start();
//        t.join();

//        lock.unlock();

        redisson.shutdown();
    }

}
