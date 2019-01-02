package com.bert.ability.test;

import com.bert.ability.proxy.DoSomeThingImplProxy;
import com.bert.ability.proxy.DoSomethingImplProxyCglib;
import com.bert.ability.service.DoSomeThing;
import com.bert.ablility.service.impl.DoSomeThingImpl;
import com.bert.ablility.service.impl.DoSomeThingImplCglib;

import java.lang.reflect.Proxy;

/**
 * Copyright (C): Bert©版权所有
 * FileName: Test
 *
 * @author caobo
 * @create 2018-12-28 16:31
 * @since 1.0.0
 * Description:
 */
public class Test {

    public static void main(String[] args) {
        //一、Jdk方式
        DoSomeThing doSomeThing = new DoSomeThingImpl();

        DoSomeThingImplProxy doSomeThingImplProxy = new DoSomeThingImplProxy(doSomeThing);

       DoSomeThing  doSomeThingProxy = (DoSomeThing) Proxy.newProxyInstance(
               DoSomeThing.class.getClassLoader(),
               new Class[]{DoSomeThing.class},
               doSomeThingImplProxy);

        doSomeThingProxy.say("beautiful life.");
        doSomeThingProxy.say("beautiful life.");
        doSomeThingProxy.say("beautiful life.");


        //二、Cglib方式

        DoSomethingImplProxyCglib doSomethingImplProxyCglib = new DoSomethingImplProxyCglib();

        DoSomeThingImplCglib doSomeThingImplCglib = (DoSomeThingImplCglib) doSomethingImplProxyCglib.getProxy(DoSomeThingImplCglib.class);

        doSomeThingImplCglib.say("beautiful life!");
        doSomeThingImplCglib.say("beautiful life!");
        doSomeThingImplCglib.say("beautiful life!");
    }
}
