package com.yty.spring6.service;

import org.springframework.stereotype.Service;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
@Service
public class AccountService {
    public void transfer(){
        System.out.println("transfering");
    }

    public void withdraw(){
        System.out.println("withdrawing");
    }
}
