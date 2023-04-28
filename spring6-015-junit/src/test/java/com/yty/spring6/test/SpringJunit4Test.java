package com.yty.spring6.test;

import com.yty.spring6.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 *
 *
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class SpringJunit4Test {
    @Autowired
    private User user;
//    @Test
//    public void testUser(){
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
//        User user = applicationContext.getBean("user", User.class);
//        System.out.println(user);
//    }

    @Test
    public void testUser2(){
        System.out.println(user);
    }


}
