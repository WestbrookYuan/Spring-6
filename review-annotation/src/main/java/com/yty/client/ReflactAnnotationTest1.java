package com.yty.client;

import com.yty.annotation.Component;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class ReflactAnnotationTest1 {
    public static void main(String[] args) throws Exception{
        Class<?> aClass = Class.forName("com.yty.bean.User");
        if (aClass.isAnnotationPresent(Component.class)) {
            Component annotation = aClass.getAnnotation(Component.class);
            String value = annotation.value();
            System.out.println(value);
        }
    }
}
