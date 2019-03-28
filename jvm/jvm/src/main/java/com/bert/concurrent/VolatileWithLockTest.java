package com.bert.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright (C):
 * FileName: VolatileTest
 *
 * @author caobo
 * @create 2019-3-6 15:04
 * @since 1.0.0
 * Description:
 * volatile在并发下是线程不安全的,通过ReentrantLock阻塞同步保证线程安全
 */
public class VolatileWithLockTest {

    public static volatile int race = 0;
    private static final int THREADS_COUNT = 20;

    static ReentrantLock lock = new ReentrantLock();

    public  static void increase(){
        try {
            lock.lock();
            race ++;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for(int i=0;i<THREADS_COUNT;i++){
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<10000;i++){
                        increase();
                    }
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() >1 ){
            Thread.yield();
        }
        System.out.println(race);

    }

}
