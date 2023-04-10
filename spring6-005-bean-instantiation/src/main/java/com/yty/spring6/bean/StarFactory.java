package com.yty.spring6.bean;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class StarFactory {
    public static Star get(){
        return new Star();
    }
}
