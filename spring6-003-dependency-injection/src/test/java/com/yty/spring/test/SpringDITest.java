package com.yty.spring.test;

import com.yty.spring.service.Impl.CustomerServiceImpl;
import com.yty.spring.service.Impl.UserServiceImpl;
import com.yty.spring.service.UserService;
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

    @Test
    public void testConstructorDI(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_construct.xml");
        CustomerServiceImpl userService = applicationContext.getBean("customerServiceBean", CustomerServiceImpl.class);
        userService.save();

        CustomerServiceImpl userService2 = applicationContext.getBean("customerServiceBean2", CustomerServiceImpl.class);
        userService2.save();

        CustomerServiceImpl userService3 = applicationContext.getBean("customerServiceBean3", CustomerServiceImpl.class);
        userService3.save();
    }

}
