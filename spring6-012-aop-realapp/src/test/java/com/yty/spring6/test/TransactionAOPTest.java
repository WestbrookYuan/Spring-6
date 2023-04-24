package com.yty.spring6.test;

import com.yty.spring6.config.Spring6Config;
import com.yty.spring6.service.AccountService;
import com.yty.spring6.service.OrderService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class TransactionAOPTest {
    @Test
    public void testTransaction(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Spring6Config.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        orderService.generate();
        System.out.println("-----");
        orderService.cancel();
        System.out.println("=====");
        AccountService accountService = applicationContext.getBean("accountService", AccountService.class);
        accountService.transfer();
        System.out.println("-----");
        accountService.withdraw();
    }
}
