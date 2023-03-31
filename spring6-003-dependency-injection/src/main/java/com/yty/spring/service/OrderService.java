package com.yty.spring.service;

import com.yty.spring.dao.OrderDao;

public class OrderService {
    private OrderDao orderDao;

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void orderGenerate(){
        orderDao.insert();
    }
}
