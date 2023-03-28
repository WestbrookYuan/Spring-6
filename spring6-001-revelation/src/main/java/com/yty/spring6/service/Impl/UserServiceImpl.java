package com.yty.spring6.service.Impl;

import com.yty.spring6.dao.Impl.UserDaoForMySQL;
import com.yty.spring6.dao.Impl.UserDaoForOracle;
import com.yty.spring6.dao.UserDao;
import com.yty.spring6.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    @Override
    public void deleteUser() {
        userDao.deleteById();
        //another codes
    }

    public void saveUser(){
    }
}
