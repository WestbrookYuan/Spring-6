package com.yty.spring.service.Impl;

import com.yty.spring.dao.UserDao;
import com.yty.spring.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public UserServiceImpl() {
    }

    @Override
    public void saveUser() {
        userDao.insert();
    }
}
