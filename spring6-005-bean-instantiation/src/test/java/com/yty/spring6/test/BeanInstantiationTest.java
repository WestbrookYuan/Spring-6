package com.yty.spring6.test;

import com.yty.spring6.bean.*;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class BeanInstantiationTest {
    @Test
    public void testBeanInstantiation1(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring.xml");
        SpringBean springBean = applicationContext.getBean("sb", SpringBean.class);
        System.out.println(springBean);
    }

    @Test
    public void testBeanInstantiation2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring.xml");
        Star starBean = applicationContext.getBean("starBean", Star.class);
        System.out.println(starBean);
    }

    @Test
    public void testBeanInstantiation3(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring.xml");
        Gun gun = applicationContext.getBean("gun", Gun.class);
        System.out.println(gun);
    }

    @Test
    public void testBeanInstantiation4(){
        BeanFactory applicationContext = new ClassPathXmlApplicationContext("Spring.xml");
        Person personBean = applicationContext.getBean("personBean", Person.class);
        System.out.println(personBean);
    }

    @Test
    public void testInjectDate(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring.xml");
        Student studentBean = applicationContext.getBean("studentBeanByFactory", Student.class);
        System.out.println(studentBean);
    }
}
