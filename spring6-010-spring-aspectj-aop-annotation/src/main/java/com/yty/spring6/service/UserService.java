package com.yty.spring6.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
@Service
public class UserService {
    public void login(){
        System.out.println("logging in");
    }

    public void logout(){
        System.out.println("logout");
    }
}
