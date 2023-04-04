package com.yty.spring.service.Impl;

import com.yty.spring.dao.Impl.MySQLDaoImpl;
import com.yty.spring.dao.Impl.OracleDaoImpl;
import com.yty.spring.dao.UserDao;
import com.yty.spring.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
    private MySQLDaoImpl mySQLDao;
    private OracleDaoImpl oracleDao;

    public CustomerServiceImpl(){}

    public CustomerServiceImpl(MySQLDaoImpl mySQLDao, OracleDaoImpl oracleDao) {
        this.mySQLDao = mySQLDao;
        this.oracleDao = oracleDao;
    }

    public void setMySQLDao(MySQLDaoImpl mySQLDao) {
        this.mySQLDao = mySQLDao;
    }

    public void setOracleDao(OracleDaoImpl oracleDao) {
        this.oracleDao = oracleDao;
    }

    @Override
    public void save() {
        mySQLDao.insert();
        oracleDao.insert();
    }
}
