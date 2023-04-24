package com.yty.aop.service;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class Aop {
    public void do1(){
        System.out.println("do 1");
    }
    public void do2(){
        System.out.println("do 1");
    }
    public void do3(){
        System.out.println("do 1");
    }
    public void do4(){
        System.out.println("do 1");
    }

    public void service(){
        try{
            // joinpoint
            do1(); // pointcut
            // joinpoint
            do2(); // pointcut
            // joinpoint
            do3(); // pointcut
            // joinpoint
            do4(); // pointcut
            // joinpoint
        }
        catch (Exception e){
            // joinpoint
        }
        finally {
            // joinpoint
        }

    }
}
