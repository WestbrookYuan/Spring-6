package com.yty.spring6.test;

import com.yty.bank.pojo.Account;
import com.yty.bank.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class SpringMybatisTest {

    @Test
    public void teseSpringMybatis(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        AccountService accountService = applicationContext.getBean("accountService", AccountService.class);
        try{
            accountService.transfer("act001", "act002", 1000.0);
            System.out.println("yeah");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
