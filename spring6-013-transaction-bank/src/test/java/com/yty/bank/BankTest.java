package com.yty.bank;

import com.yty.bank.config.Spring6Config;
import com.yty.bank.pojo.Account;
import com.yty.bank.service.AccountService;
import com.yty.bank.service.impl.IsolationService1;
import com.yty.bank.service.impl.IsolationService2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class BankTest {
    @Test
    public void testTransfer(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        AccountService accountService = applicationContext.getBean("accountService", AccountService.class);
        try{
            accountService.transfer("act001", "act002", 500.0);
            System.out.println("transfer succeed");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testPropagation(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        AccountService accountService = applicationContext.getBean("accountService", AccountService.class);
        accountService.save(new Account("act-007", 100.0));

    }

    @Test
    public void testIsolation1(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        IsolationService1 i1 = applicationContext.getBean("i1", IsolationService1.class);
        i1.getbyActno("act00001");
    }
    @Test
    public void testIsolation2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        IsolationService2 i2 = applicationContext.getBean("i2", IsolationService2.class);
        i2.insert(new Account("act000110", 1000.0));
    }

    @Test
    public void testNoXML(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Spring6Config.class);
        AccountService accountService = applicationContext.getBean("accountService", AccountService.class);
        try{
            accountService.transfer("act001", "act002", 500.0);
            System.out.println("transfer succeed");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
