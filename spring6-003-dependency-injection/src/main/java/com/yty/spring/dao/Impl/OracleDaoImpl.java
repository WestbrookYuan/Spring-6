package com.yty.spring.dao.Impl;

import com.yty.spring.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OracleDaoImpl implements UserDao {
    private final Logger logger = LoggerFactory.getLogger(OracleDaoImpl.class);
    @Override
    public void insert() {
        logger.info("Oracle inserting");
    }

    }
