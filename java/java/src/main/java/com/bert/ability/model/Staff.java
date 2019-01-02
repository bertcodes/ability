package com.bert.ability.model;

import java.io.Serializable;

/**
 * Copyright (C): Bert©版权所有
 * FileName: Staff
 *
 * @author caobo
 * @create 2018-12-29 16:12
 * @since 1.0.0
 * Description:
 */
public class Staff implements Cloneable,Serializable{
    private static final long serialVersionUID = 1L;

    public Staff() {
        System.out.println("Staff Constructor called...");
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Staff staff = (Staff) o;

        return name != null ? name.equals(staff.name) : staff.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                '}';
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
