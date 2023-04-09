package com.yty.spring6.test;

import com.yty.spring6.bean.SpringBean;
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
}
