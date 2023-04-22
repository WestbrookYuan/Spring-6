package com.yty.proxy.client;

import com.yty.proxy.service.OrderService;
import com.yty.proxy.service.handlers.TimerInvocationHandler;
import com.yty.proxy.service.impl.OrderServiceImpl;
import com.yty.proxy.util.ProxyUtil;

import java.lang.reflect.Proxy;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class Client {

    public static void main(String[] args) {
        // target object
        OrderService target = new OrderServiceImpl();
        // proxy object
        /*
         * 新建代理实例
         * 1. 在内存中动态生成了代理类
         * 2. new了个对象
         */
        OrderService proxyObj = (OrderService) ProxyUtil.newProxyInstance(target);
        // proxy object methods
        proxyObj.generate();
        proxyObj.modify();
        proxyObj.detail();
        String name = proxyObj.getName();
        System.out.println(name);


    }

}
