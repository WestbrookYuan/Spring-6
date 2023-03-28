package com.yty.spring6.dao.Impl;

import com.yty.spring6.dao.UserDao;

public class UserDaoForMySQL implements UserDao {

    @Override
    public void deleteById() {
        System.out.println("Mysql deleting");
    }
}
