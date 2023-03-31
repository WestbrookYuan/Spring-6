package com.yty.spring.test;

import com.yty.spring.bean.SimpleValueType;
import com.yty.spring.bean.User;
import com.yty.spring.jdbc.MyDataSource;
import com.yty.spring.service.Impl.CustomerServiceImpl;
import com.yty.spring.service.Impl.UserServiceImpl;
import com.yty.spring.service.OrderService;
import com.yty.spring.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

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

    @Test
    public void testSetOutsideBean(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("set-di.xml");
        OrderService orderService = applicationContext.getBean("orderServiceBean", OrderService.class);
        orderService.orderGenerate();

        OrderService orderService2 = applicationContext.getBean("orderServiceBean2", OrderService.class);
        orderService2.orderGenerate();

    }

    @Test
    public void testSetSimpleData(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("set-di.xml");
        User userBean = applicationContext.getBean("userBean", User.class);
        System.out.println(userBean);
    }

    @Test
    public void testSetSimpleDataType(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("set-di.xml");
        SimpleValueType simpleValueType = applicationContext.getBean("simpleValueTypeBean", SimpleValueType.class);
        System.out.println(simpleValueType);
    }

    @Test
    public void testMyDataSource(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("set-di.xml");
        MyDataSource myDataSource = applicationContext.getBean("myDataSourceBean", MyDataSource.class);
        System.out.println(myDataSource);
    }
}
