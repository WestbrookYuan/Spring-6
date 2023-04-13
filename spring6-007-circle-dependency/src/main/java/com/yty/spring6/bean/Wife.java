package com.yty.spring6.bean;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class Wife {
    private String name;
    private Husband husband;

    public void setName(String name) {
        this.name = name;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }


    public String getName() {
        return name;
    }

    public Husband getHusband() {
        return husband;
    }

    @Override
    public String toString() {
        return "Wife{" +
                "name='" + name + '\'' +
                ", husband=" + husband.getName() +
                '}';
    }
}
