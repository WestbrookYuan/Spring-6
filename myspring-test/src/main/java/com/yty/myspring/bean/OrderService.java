package com.yty.myspring.bean;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class OrderService {
    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
    public void generateOrder(){
        orderDao.insert();
    }
}
