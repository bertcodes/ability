package com.bert.ability;

/**
 * Copyright (C): Daemon©版权所有
 * FileName: CanReliveObj
 *
 * @author caobo
 * @create 2018-12-2 18:34
 * @since 1.0.0
 * Description:
 */
public class CanReliveObj { 
    public static CanReliveObj obj;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("CanReliveObj finalize called");
        obj = this;
    }
    @Override
    public String toString() {
        return "I am CanReliveObj";
    }

    public static void main (String[] args) throws InterruptedException{
        obj = new CanReliveObj();
        obj = null;//可复活
        System.gc();
        Thread.sleep(1000);
        if(obj == null){
            System.out.println("obj is null");
        }else{
            System.out.println("obj is available");
        }
        System.out.println("第二次 gc");
        obj = null;//不可复活
        System.gc();
        Thread.sleep(1000);
        if(obj == null){
            System.out.println("obj is null");
        }else{
            System.out.println("obj is available");
        }
    }
}
