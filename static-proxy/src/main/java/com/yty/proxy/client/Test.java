package com.yty.proxy.client;

import com.yty.proxy.service.OrderService;
import com.yty.proxy.service.impl.OrderServiceImpl;
import com.yty.proxy.service.impl.OrderServiceImplSub;
import com.yty.proxy.service.impl.OrderServiceProxy;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class Test {
    public static void main(String[] args) {
//        OrderService orderService = new OrderServiceImplSub();
//        orderService.generate();
//        orderService.modify();
//        orderService.detail();

        // target object
        OrderService orderService = new OrderServiceImpl();
        // proxy object
        OrderService orderServiceProxy = new OrderServiceProxy(orderService);
        // proxy object methods
        orderServiceProxy.generate();
        orderServiceProxy.modify();
        orderServiceProxy.detail();
    }
}
