package com.bert.ability.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Copyright (C): Bert©版权所有
 * FileName: DoSomethingImplProxyCglib
 *
 * @author caobo
 * @create 2018-12-29 10:37
 * @since 1.0.0
 * Description:
 */
public class DoSomethingImplProxyCglib implements MethodInterceptor{

    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clasz){

        enhancer.setSuperclass(clasz);
        enhancer.setCallback(this);
        return enhancer.create();

    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        long startTime =  System.currentTimeMillis();
        System.out.println(method.getName() + " 方法调用开始(Cglib方式)>>>");
        Object result = proxy.invokeSuper(obj,args);
        Thread.sleep(1000);
        long endTime = System.currentTimeMillis();
        System.out.println(method.getName() + " 方法调用结束。耗时： "+(endTime-startTime)+" ms  \n");

        return result;
    }

}
