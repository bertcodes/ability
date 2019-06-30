package com.bert.ability;

/**
 * Copyright (C):
 * FileName: TestAllocation
 *
 * @author caobo
 * @create 2019-3-29 10:49
 * @since 1.0.0
 * Description:
 */
public class TestAllocation {

    private static final int _1MB = 1024 * 1024;

    /**
     * 对象优先在Eden分配
     * 发生Minor GC新生代6M对象晋升到老年代
     * VM参数：
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
     */
    public static void TestAllocation() {

        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];//出现一次Minor GC
    }
    /**
     * 优化大对象直接进入老年代
     * VM参数：
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:PretenureSizeThreshold=3145728
     * 参数PretenureSizeThreshold单位是byte只对Serial和ParNew两款收集器有效，Parallel Scavenge收集器不认识这个参数
     * 如遇到使用此参数可考虑ParNew加CMS是收集器组合
     * */
    public static void TestPretenureSizeThreshold() {

        byte[] allocation;
        allocation = new byte[4 * _1MB];
    }
    /**
     * 长期存活的对象将进入老年代
     * VM参数：
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution -XX:TargetSurvivorRatio=90
     * */
    public static void TestTenuringThreshold() {

        byte[] allocation1,allocation2,allocation3;

        allocation1 = new byte[_1MB/4];//什么时候进入老年代取决于XX:MaxTenuringThreshold设置
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];

        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
    }

    /**
     * 动态对象年龄判定
     * VM参数：
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
     * */
    public static void TestTenuringThreshold2() {

        byte[] allocation1,allocation2,allocation3,allocation4;

        allocation1 = new byte[_1MB/4];//allocation1+allocation2大于survivor空间一半
//        allocation2 = new byte[_1MB/4];
        allocation3 = new byte[4 * _1MB];
        allocation4 = new byte[4 * _1MB];

        allocation4 = null;
        allocation4 = new byte[4 * _1MB];
    }

    public static void main(String[] args) {

//        TestAllocation();//对象优先在Eden分配
//        TestPretenureSizeThreshold();//配置优化大对象直接进入老年代
//        TestTenuringThreshold();//长期存活的对象将进入老年
          TestTenuringThreshold2();

    }

}
