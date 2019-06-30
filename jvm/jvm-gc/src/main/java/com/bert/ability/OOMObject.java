package com.bert.ability;

import jdk.internal.cmm.SystemResourcePressureImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C):
 * FileName: OOMObject
 *
 * @author caobo
 * @create 2019-4-1 17:47
 * @since 1.0.0
 * Description:
 */
public class OOMObject {

    static class OObject{
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fileHeap(int mum) throws InterruptedException {
        List<OObject> list = new ArrayList<OObject>();
        for(int i=0;i<mum;i++){
            Thread.sleep(50);
            list.add(new OObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        fileHeap(1000);
    }

}
