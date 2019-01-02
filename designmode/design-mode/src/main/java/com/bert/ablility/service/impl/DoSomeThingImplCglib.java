package com.bert.ablility.service.impl;

/**
 * Copyright (C): Bert©版权所有
 * FileName: DoSomeThingImplCglib
 *
 * @author caobo
 * @create 2018-12-29 10:41
 * @since 1.0.0
 * Description:
 */
public class DoSomeThingImplCglib {

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
