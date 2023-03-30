package com.yty.spring.test;

import com.yty.spring.service.Impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDITest {

    @Test
    public void testSetDI(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring.xml");
        UserServiceImpl userService = applicationContext.getBean("userServiceBean", UserServiceImpl.class);
        userService.saveUser();
    }

}
