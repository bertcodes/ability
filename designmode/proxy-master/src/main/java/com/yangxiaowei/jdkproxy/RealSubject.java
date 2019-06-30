package com.yangxiaowei.jdkproxy;

/**
 * @Author: bertCao
 * @Date: 2019/6/29 7:32
 * @Description
 */
public class RealSubject implements Subject {

    @Override
    public void speak(String lang) {
        System.out.println("说"+lang);
    }
}
