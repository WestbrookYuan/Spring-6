package com.yty.spring.service.Impl;

import com.yty.spring.dao.Impl.MySQLDaoImpl;
import com.yty.spring.dao.UserDao;
import com.yty.spring.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
    private UserDao mySQLDao;
    private UserDao oracleDao;

    public CustomerServiceImpl(){}

    public CustomerServiceImpl(UserDao mySQLDao, UserDao oracleDao) {
        this.mySQLDao = mySQLDao;
        this.oracleDao = oracleDao;
    }


    @Override
    public void save() {
        mySQLDao.insert();
        oracleDao.insert();
    }
}
