package com.bert.memory;

/**
 * Copyright (C):
 * FileName: RuntimeConstantPoolOOM
 *
 * @author caobo
 * @create 2019-3-25 17:26
 * @since 1.0.0
 * Description:
 * JDK1.8
 * intern()方法返回的引用是首次在堆中实例化的地址，"计算机软件"是首次创建的输出true，“java”这个字符串
 * 在执行StringBuilder.toString()之前已经出现过输出false
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);


    }
}
