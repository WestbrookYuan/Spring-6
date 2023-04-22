package com.yty.proxy.service.impl;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class OrderServiceImplSub extends OrderServiceImpl{
    @Override
    public void generate() {
        long begin = System.currentTimeMillis();
        super.generate();
        System.out.println( "time consume: " + (System.currentTimeMillis() - begin));
    }

    @Override
    public void modify() {
        long begin = System.currentTimeMillis();
        super.modify();
        System.out.println( "time consume: " + (System.currentTimeMillis() - begin));

    }

    @Override
    public void detail() {
        long begin = System.currentTimeMillis();
        super.detail();
        System.out.println( "time consume: " + (System.currentTimeMillis() - begin));

    }
}
