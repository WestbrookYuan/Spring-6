package com.yty.spring.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderDao {
    private final static Logger logger = LoggerFactory.getLogger(OrderDao.class);
    public void insert(){
        logger.info("generate order");
    }
}
