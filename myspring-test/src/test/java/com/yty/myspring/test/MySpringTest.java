package com.yty.myspring.test;

import com.yty.myspring.bean.OrderService;
import org.junit.Test;
import org.myspringframework.core.ApplicationContext;
import org.myspringframework.core.impl.ClassPathXmlApplicationContext;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class MySpringTest {

    @Test
    public void testMySpring(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Object vip = applicationContext.getBean("vip");
        System.out.println(vip);
        OrderService orderService = (OrderService) applicationContext.getBean("orderService");
        orderService.generateOrder();

    }
}
