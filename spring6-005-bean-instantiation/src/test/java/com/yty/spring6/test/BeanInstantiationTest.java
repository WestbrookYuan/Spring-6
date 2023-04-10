package com.yty.spring6.test;

import com.yty.spring6.bean.Gun;
import com.yty.spring6.bean.Person;
import com.yty.spring6.bean.SpringBean;
import com.yty.spring6.bean.Star;
import org.junit.Test;
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
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring.xml");
        Person personBean = applicationContext.getBean("personBean", Person.class);
        System.out.println(personBean);
    }
}
