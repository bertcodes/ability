package com.yangxiaowei.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: bertCao
 * @Date: 2019/6/29 7:32
 * @Description
 */
public class MyInvocationHandler implements InvocationHandler {

    Subject realSubject;

    MyInvocationHandler(Subject realSubject){
        this.realSubject = realSubject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        method.invoke(realSubject, args);
        System.out.println("after");
        return  null ;
    }
}
