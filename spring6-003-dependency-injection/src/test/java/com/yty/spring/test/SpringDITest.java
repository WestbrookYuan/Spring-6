package com.yty.spring.test;

import com.yty.spring.bean.*;
import com.yty.spring.jdbc.MyDataSource;
import com.yty.spring.jdbc.MyDataSource1;
import com.yty.spring.jdbc.MyDataSource2;
import com.yty.spring.service.CustomerService;
import com.yty.spring.service.Impl.CustomerServiceImpl;
import com.yty.spring.service.Impl.UserServiceImpl;
import com.yty.spring.service.OrderService;
import org.apache.logging.log4j.core.config.plugins.PluginLoggerContext;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class SpringDITest {

    @Test
    public void testSetDI(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring.xml");
        UserServiceImpl userService = applicationContext.getBean("userServiceBean", UserServiceImpl.class);
        userService.saveUser();
    }

    @Test
    public void testConstructorDI(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-construct.xml");
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

    @Test
    public void testCascade(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("cascade.xml");
        Student studentBean = applicationContext.getBean("studentBean", Student.class);
        Clazz clazzBean = applicationContext.getBean("clazzBean", Clazz.class);
        System.out.println(studentBean);
        System.out.println(clazzBean);
    }

    @Test
    public void testArrayInjection(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-array.xml");
        YuQian yuQian = applicationContext.getBean("yuQianBean", YuQian.class);
        System.out.println(yuQian);

    }

    @Test
    public void testCollectionInjection(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-collection.xml");
        Person person = applicationContext.getBean("personBean", Person.class);
        System.out.println(person);
        List<String> names = person.getNames();
        names.forEach(name->{
            System.out.println(name);
        });
        System.out.println("---");
        Set<String> addresses = person.getAddresses();
        addresses.forEach(address ->{
            System.out.println(address);
        });
        System.out.println("---");
        Map<Integer, String> phones = person.getPhones();
        Set<Map.Entry<Integer, String>> entries = phones.entrySet();
        entries.forEach(entry->{
            System.out.println(entry);
        });
        System.out.println("---");
        Properties properties = person.getProperties();
        System.out.println(properties);

    }

    @Test
    public void testNull(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-collection.xml");
        Cat catBean = applicationContext.getBean("catBean", Cat.class);
        System.out.println(catBean.getName().toUpperCase());
    }

    @Test
    public void testSpecialChar(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-collection.xml");
        MathBean mathBean = applicationContext.getBean("mathBean", MathBean.class);
        System.out.println(mathBean);
    }

    @Test
    public void testP(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-p.xml");
        Dog dogBean = applicationContext.getBean("dogBean", Dog.class);
        System.out.println(dogBean);
    }

    @Test
    public void testC(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-c.xml");
        People peopleBean = applicationContext.getBean("peopleBean", People.class);
        System.out.println(peopleBean);
    }

    @Test
    public void testUtil(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-util.xml");
        MyDataSource1 myDataSource1 = applicationContext.getBean("myDataSource1Bean", MyDataSource1.class);
        MyDataSource2 myDataSource2 = applicationContext.getBean("myDataSource2Bean", MyDataSource2.class);
        System.out.println(myDataSource1);
        System.out.println(myDataSource2);
    }

    @Test
    public void testAutoWirebyName(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-autowire.xml");
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        orderService.orderGenerate();
    }

    @Test
    public void testAutoWirebyType(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-autowire.xml");
        CustomerService orderService = applicationContext.getBean("customService", CustomerServiceImpl.class);
        orderService.save();
    }

    @Test
    public void testOutsideConfig(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-properties.xml");
        MyDataSource myDataSource = applicationContext.getBean("myDataSource", MyDataSource.class);
        System.out.println(myDataSource);
    }

}
