package com.example.xiaogmulanxi.bean;

public class JavaBean {
    private String name;
    private String sex;

    @Override
    public String toString() {
        return "JavaBean{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public JavaBean() {
    }

    public JavaBean(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
