package com.yty.spring6.bean3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
@Component
public class Product {

    private String name;

    private int age;

    @Value("24")
    public void setAge(int age) {
        this.age = age;
    }

    @Value("wjs")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
