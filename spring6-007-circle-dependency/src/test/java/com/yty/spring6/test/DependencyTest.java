package com.yty.spring6.test;

import com.yty.spring6.bean.Husband;
import com.yty.spring6.bean.Wife;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class DependencyTest {
    @Test
    public void testCD(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        System.out.println(husband);
        Object wife = applicationContext.getBean("wife", Wife.class);
        System.out.println(wife);
    }

    @Test
    public void testCD2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring2.xml");
        com.yty.spring6.bean2.Husband husband = applicationContext.getBean("h", com.yty.spring6.bean2.Husband.class);
        System.out.println(husband);
        com.yty.spring6.bean2.Wife wife = applicationContext.getBean("w", com.yty.spring6.bean2.Wife.class);
        System.out.println(wife);
    }
}
