package com.yty.spring6.dao.Impl;

import com.yty.spring6.dao.UserDao;

public class UserDaoForOracle implements UserDao {
    @Override
    public void deleteById() {
        System.out.println("oracle deleting");
    }
}
