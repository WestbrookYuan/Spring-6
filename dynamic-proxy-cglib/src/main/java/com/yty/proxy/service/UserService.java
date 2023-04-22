package com.yty.proxy.service;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class UserService {
    public boolean login(String username, String password){
        System.out.println("verifying");
        return "admin".equals(username) && "1234".equals(password);
    }

    public void logout(){
        System.out.println("quiting");
    }
}
