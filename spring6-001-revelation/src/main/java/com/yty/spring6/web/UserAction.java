package com.yty.spring6.web;

import com.yty.spring6.service.Impl.UserServiceImpl;
import com.yty.spring6.service.UserService;

public class UserAction {
    private UserService userService;
    public void deleteRequest(){
        userService.deleteUser();
    }
}
