package com.yty.spring.bean;

import java.util.Date;

public class SimpleValueType {
    private int age;
    private Integer age2;

    private boolean flag;
    private Boolean flag2;

    private char c;
    private Character c2;
    private Season season;

    private Class clazz;

    @Override
    public String toString() {
        return "SimpleValueType{" +
                "age=" + age +
                ", age2=" + age2 +
                ", flag=" + flag +
                ", flag2=" + flag2 +
                ", c=" + c +
                ", c2=" + c2 +
                ", season=" + season +
                ", clazz=" + clazz +
                ", birthday=" + birthday +
                '}';
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    private Date birthday;

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getAge2() {
        return age2;
    }

    public void setAge2(Integer age2) {
        this.age2 = age2;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Boolean getFlag2() {
        return flag2;
    }

    public void setFlag2(Boolean flag2) {
        this.flag2 = flag2;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public Character getC2() {
        return c2;
    }

    public void setC2(Character c2) {
        this.c2 = c2;
    }

}
