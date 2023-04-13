package com.yty.spring6.bean2;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class Wife {
    private String name;
    private Husband husband;


    public Wife(String name, Husband husband) {
        this.name = name;
        this.husband = husband;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Wife{" +
                "name='" + name + '\'' +
                ", husband=" + husband.getName() +
                '}';
    }
}
