package com.bert.ability;

/**
 * Copyright (C): Daemon©版权所有
 * FileName: LambdaTest
 *
 * @author caobo
 * @create 2018-11-24 12:35
 * @since 1.0.0
 * Description:Lambda 表达式实例
 * Lambda 表达式，也可称为闭包，它是推动Java8发布的最重要新特性。
 * Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）。
 * 使用 Lambda表达式可以是代码更变的更加简洁紧凑。
 *
 * 使用Lambda表达式需要注意下面两点：
 * 1、Lambda表达式主要用来定义行内执行的方法类型接口，例如，一个简单的接口。在下面例子中
 * ，我们使用各种类型的Lambda表达式来定义MathOperation接口的方法。然后我们定义可SayMessage
 * 的执行。
 * 2、Lambda表达式免去使用匿名函数方法的麻烦，并且给予Java简单但强大的函数化的编程能力
 *
 */
public class LambdaSampleTest {

    public static void main(String args[]) {
        LambdaSampleTest lambdaSampleTest = new LambdaSampleTest();
        //类型声明
        MathOperation addition  = (int a,int b)->a+b;
        //不用类型声明
        MathOperation subtraction = (a,b)->a-b;
        //大括号中的返回语句
        MathOperation multiplication = (int a,int b)->{return a*b;};
        //没有大括号及返回语句
        MathOperation division = (int a,int b)->a/b;

        System.out.println("10 + 5 = "+ lambdaSampleTest.operate(10,5,addition));
        System.out.println("10 - 5 = " + lambdaSampleTest.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + lambdaSampleTest.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + lambdaSampleTest.operate(10, 5, division));

        //不用括号
        GreatingService greatingService1 = message ->
                System.out.println("Hello "+message);
        GreatingService greatingService2 = (message ) ->
                System.out.println("Hello "+message);

        greatingService1.sayMessage("github");
        greatingService2.sayMessage("google");

    }

    interface MathOperation{
        int operation(int a,int b);
    }
    interface GreatingService{
        void sayMessage(String message);
    }
    private int operate(int a,int b,MathOperation mathOperation){
        return mathOperation.operation(a,b);
    }
}
