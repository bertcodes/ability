package com.yangxiaowei.cglib;

import org.springframework.cglib.core.DebuggingClassWriter;

/**
 * @Author: bertCao
 * @Date: 2019/6/29 7:32
 * @Description
 */
public class CglibMainTest {
    public static void main(String[] args) {
        //这一句用来在磁盘生成动态代理类
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"D:\\IdeaProjects\\proxy-master\\src\\main\\java\\com\\yangxiaowei\\cglib");
        Engineer engineerProxy = (Engineer) CglibProxy.getProxy(new Engineer());
        engineerProxy.eat();
        engineerProxy.work();
    }
}
