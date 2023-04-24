package com.yty.spring6.biz;

import org.springframework.stereotype.Service;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
@Service
public class UserService {
    public void saveUser(){
        System.out.println("saving");
    }
    public void deleteUser(){
        System.out.println("deleting");
    }
    public void updateUser(){
        System.out.println("updating");
    }
    public void searchUser(){
        System.out.println("searching");
    }
}
