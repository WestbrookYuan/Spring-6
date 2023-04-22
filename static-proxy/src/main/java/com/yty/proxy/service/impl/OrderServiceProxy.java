package com.yty.proxy.service.impl;

import com.yty.proxy.service.OrderService;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 * 代理对象要与目标对象要有相同的行为，就要实现同一些或同一个接口
 **/
public class OrderServiceProxy implements OrderService {

    //将目标对象作为代理对象的一个属性，关联对象，比继承对象的耦合度低
    // 属性类型：公共接口，降低耦合度
    private OrderService target;

    public OrderServiceProxy() {
    }

    public OrderServiceProxy(OrderService target) {
        this.target = target;
    }

    public void setTarget(OrderService target) {
        this.target = target;
    }

    @Override
    public void generate() { //代理方法 代替目标方法
        // enhance
        long begin = System.currentTimeMillis();
        // 调用目标对象的目标方法
        target.generate();
        long end = System.currentTimeMillis();
        System.out.println("time consume: " + (end - begin));

    }

    @Override
    public void modify() {
        long begin = System.currentTimeMillis();
        target.modify();
        long end = System.currentTimeMillis();
        System.out.println("time consume: " + (end - begin));
    }

    @Override
    public void detail() {
        long begin = System.currentTimeMillis();
        target.detail();
        long end = System.currentTimeMillis();
        System.out.println("time consume: " + (end - begin));
    }
}
