package com.yty.spring6.service;

import org.springframework.stereotype.Service;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
@Service
public class OrderService {
    public void generate(){
        System.out.println("generating");
        String s = null;
        s.toString();
    }
    public void cancel(){
        System.out.println("canceling");
    }
}
