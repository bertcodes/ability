package com.bert.ablility.service.impl;

import com.bert.ability.service.DoSomeThing;

/**
 * Copyright (C): Bert©版权所有
 * FileName: DoSomeThingImpl
 *
 * @author caobo
 * @create 2018-12-28 16:11
 * @since 1.0.0
 * Description:
 */
public class DoSomeThingImpl implements DoSomeThing{

    public <T> boolean say(T t) {
        if(t == null){
            return false;
        }
        if(t instanceof String){
            System.out.println(t);
        }
        return true;
    }

}
