package com.yty.spring6.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
@Service
public class OrderService {
    public void generate(){
        System.out.println("generate");
//        if (1 == 1){
//            throw new RuntimeException("runtime exception");
//        }
    }
}
