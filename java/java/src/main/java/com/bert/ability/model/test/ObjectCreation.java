package com.bert.ability.model.test;

import com.bert.ability.model.Staff;

import java.io.*;
import java.lang.reflect.Constructor;

/**
 * Copyright (C): Bert©版权所有
 * FileName: ObjectCreation
 *
 * @author caobo
 * @create 2018-12-29 16:18
 * @since 1.0.0
 * Description:
 */
public class ObjectCreation {

    public static void main(String[] args) throws Exception {

        //一、By using new keyword
        Staff sta1 = new Staff();
        sta1.setName("Damon");
        System.out.println(sta1 + ",hashcode: " + sta1.hashCode() +"\n");

        //二、By using Class class's newInsrance() method
        Staff sta2 = (Staff)Class.forName("com.bert.ability.model.Staff").newInstance();
        //Or we can simply do this
        //Staff sta2 = Staff.class.newInstance();
        sta2.setName("Elena");
        System.out.println(sta2 + ",hashcode: " + sta2.hashCode() +"\n");

        //三、By using Constructor class's newInstance() method
        Constructor<Staff> constructor = Staff.class.getConstructor();
        Staff sta3 = constructor.newInstance();
        sta3.setName("Scofield");
        System.out.println(sta3 + ",hashcode: " + sta3.hashCode() +"\n");

        //四、By suing clone() method
        Staff sta4 = (Staff)sta3.clone();
        sta4.setName("Lisa");
        System.out.println(sta4 + ",hashcode: " + sta4.hashCode() +"\n");

        //五、By using Deserialization
        //Serialization
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.obj"));
        out.writeObject(sta4);
        out.close();

        //Deserialization
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.obj"));
        Staff sta5 = (Staff)in.readObject();
        in.close();
        sta5.setName("Bert");
        System.out.println(sta5 + ",hashcode: " + sta5.hashCode() +"\n");


    }

}
