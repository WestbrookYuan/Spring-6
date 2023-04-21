package com.yty.dao.Impl;

import com.yty.dao.OrderDao;
import org.springframework.stereotype.Repository;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
// @Repository("orderDaoImplOracle")
public class OrderDaoImplOracle implements OrderDao {
    @Override
    public void insert() {
        System.out.println("oracle inserting");
    }
}
