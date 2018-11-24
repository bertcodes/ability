package com.bert.ability;

/**
 * Copyright (C): Daemon©版权所有
 * FileName: LambdaVariableScopeTest
 *
 * @author caobo
 * @create 2018-11-24 13:42
 * @since 1.0.0
 * Description: Lambda变量作用域
  Lambda表达式只能引用标记了final的外层局部变量，这就是说不能在Lambda内部修改
 定义在域外的局部变量，否则会编译报错
 */
public class LambdaVariableScopeTest {
    final static String salutaion = "Hello";

    public static void main(String[] args) {
        //1)
//        GreetingService greetingService = message ->
//                System.out.println(salutaion + message);
//        greetingService.sayMessage(" bert");
        //2)
         int num = 1;
        Converter<Integer,String> s = (param)->
                System.out.println(String.valueOf(param+num));
        s.convert(2);
        //Error:(25, 57) java: 从lambda 表达式引用的本地变量必须是最终变量或实际上的最终变量
        //Lambda内部的局部变量可以不声明为final，但是必须不可被后面的修改（即隐形的具有final语义）
//        num = 5;
    }

    interface GreetingService{
        void sayMessage(String message);
    }

    public interface Converter<T1 , T2>{
        void convert(int id);
    }
}
