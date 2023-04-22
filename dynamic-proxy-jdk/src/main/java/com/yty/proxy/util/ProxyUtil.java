package com.yty.proxy.util;

import com.yty.proxy.service.OrderService;
import com.yty.proxy.service.handlers.TimerInvocationHandler;

import java.lang.reflect.Proxy;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class ProxyUtil {
    private ProxyUtil(){}

    public static Object newProxyInstance(Object target){
        OrderService proxyObj = (OrderService) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new TimerInvocationHandler(target));

        return proxyObj;
    }
}
