package com.yty.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class Test2 {
    public static void main(String[] args) throws Exception {
        //获取这个类
        Class<?> clazz = Class.forName("com.yty.reflect.SomeService");
        //获取方法
        Method doSomeMethod = clazz.getDeclaredMethod("doSome", String.class, int.class);
        //四要素：调用哪个对象，使用哪个方法，传递哪个参数，返回什么结果
//        Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
//        Object o = declaredConstructor.newInstance();
        Object o = clazz.newInstance();
        Object lisi = doSomeMethod.invoke(o, "lisi", 250);
        System.out.println(lisi);
    }
}
