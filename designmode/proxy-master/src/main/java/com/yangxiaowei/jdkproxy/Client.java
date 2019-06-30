package com.yangxiaowei.jdkproxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * @Author: bertCao
 * @Date: 2019/6/29 7:32
 * @Description
 */
public class Client {
    public static void main(String[] args) {
//        saveProxyFile("D:\\IdeaProjects\\proxy-master\\src\\main\\java\\com\\yangxiaowei\\jdkproxy\\");
        //这一句用来在磁盘生成动态代理类
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Subject realSubject = new RealSubject();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(realSubject);

        Subject proxyClass = (Subject) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),new Class[]{Subject.class},myInvocationHandler);

        proxyClass.speak("english");

    }
    /**
     * 保存 JDK 动态代理生产的类
     * @param filePath 保存路径，默认在项目路径下生成 $Proxy0.class 文件
     */
    private static void saveProxyFile(String... filePath) {
        if (filePath.length != 0) {
            System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        } else {
            FileOutputStream out = null;
            try {
                byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", RealSubject.class.getInterfaces());
                out = new FileOutputStream(filePath[0] + "$Proxy0.class");
                out.write(classFile);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.flush();
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
