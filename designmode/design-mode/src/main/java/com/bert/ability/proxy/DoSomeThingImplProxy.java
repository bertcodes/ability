package com.bert.ability.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Copyright (C): Bert©版权所有
 * FileName: DoSomeThingImplProxy
 *
 * @author caobo
 * @create 2018-12-28 16:20
 * @since 1.0.0
 * Description:
 */
public class DoSomeThingImplProxy implements InvocationHandler{
    private Object subject;

    public DoSomeThingImplProxy(Object subject) {
        this.subject = subject;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        long startTime =  System.currentTimeMillis();
        System.out.println(method.getName() + " 方法调用开始(Jdk方式)>>>");
        Object result = method.invoke(subject,args);
        Thread.sleep(1000);
        long endTime = System.currentTimeMillis();
        System.out.println(method.getName() + " 方法调用结束。耗时： "+(endTime-startTime)+" ms \n");
        return result;

    }
}
