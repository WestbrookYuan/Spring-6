package com.yty.proxy.service.impl;

import com.yty.proxy.service.OrderService;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 * 目标对象
 **/
public class OrderServiceImpl implements OrderService {

    /**
     * 统计耗时
     * 1. 每个方法统计
     *    缺点 违背OCP
     *        代码复用能力差
     * 2. 编写业务类的子类，对每个方法进行重写
     *    缺点 虽然解决了OCP原则，但代码耦合度很高
     *        代码复用
     *
     * 3. 代理模式
     *
     */
    @Override
    public void generate() {
        // 模拟耗时
        try{
            Thread.sleep(1234);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("generate");
    }

    @Override
    public void modify() {
        try{
            Thread.sleep(1234);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("modify");
    }

    @Override
    public void detail() {
        try{
            Thread.sleep(1234);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("detail");
    }
}
