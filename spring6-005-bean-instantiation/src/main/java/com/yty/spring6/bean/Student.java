package com.yty.spring6.bean;

import java.util.Date;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class Student {
    private Date birth;
    // Date被看作简单类型，但value要求严格
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Student{" +
                "birth=" + birth +
                '}';
    }
}
