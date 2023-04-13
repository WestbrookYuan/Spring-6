package com.yty.reflect;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class SomeService {
    public void doSome(){
        System.out.println("running public void doSome");
    }

    public String doSome(String s){
        System.out.println("running public String doSome(String s)");
        return s;
    }

    public String doSome(String s, int i){
        System.out.println("running public String doSome(String s, int i)");
        return s + i;
    }
}
