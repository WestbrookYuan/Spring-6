package com.yty.reflect;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class Test {
    public static void main(String[] args) {
        /**
         * 分析调用方法的要素
         * 1. 调用哪个对象的哪个方法，传什么参数，返回什么结果
         * 使用反射机制也要这四要素
         */
        SomeService someService = new SomeService();
        someService.doSome();
        String s = someService.doSome("1");
        System.out.println(s);
        String s1 = someService.doSome("1", 150);
        System.out.println(s1);
    }
}
