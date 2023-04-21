package com.yty.service;

import com.yty.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
@Service
public class OrderService {
    //不需要任何属性，直接使用这个注解即可
    //根据ByType进行自动装配
    // @Autowired
    private OrderDao orderDao;

    //@Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
    // @Autowired
/*    public OrderService(@Autowired OrderDao orderDao) {
        this.orderDao = orderDao;
    }*/

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void generate(){
        orderDao.insert();
    }

}
