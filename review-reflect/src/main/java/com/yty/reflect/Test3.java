package com.yty.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class Test3 {
    public static void main(String[] args) throws Exception {
        Class<?> aClass = Class.forName("com.yty.reflect.SomeService");
        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
        Object o = declaredConstructor.newInstance();
        Method doSome = aClass.getDeclaredMethod("doSome", String.class);
        Object sb = doSome.invoke(o, "sb");
        System.out.println(sb);
    }
}
