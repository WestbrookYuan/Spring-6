package com.yty.spring.test;

import com.yty.spring.bean.User;
import com.yty.spring.dao.UserDaoImplForMySQL;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Date;

public class FirstSpringTest {

    @Test
    public void firstSpringTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //get spring beanFactory object
        // ApplicationContext is an interface, has many abstarct methods
        // use ClassPathXmlApplicationContext can load spring config from resource folder
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml", "beans.xml", "xml/bean2.xml");
        //get a bean
        Object userBean = applicationContext.getBean("userBean");
        System.out.println(userBean);

        Object vipBean = applicationContext.getBean("vipBean");
        System.out.println(vipBean);
        Object userBean2 = applicationContext.getBean("userBean2");
        System.out.println(userBean2);
//        Class<?> aClass = Class.forName("com.yty.spring.bean.User");
//        Object o = aClass.newInstance();
//        System.out.println(o);
    }

    @Test
    public void testGetDateBean(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
//        Object nowTime = applicationContext.getBean("dateBean");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
//        String strNowTime = sdf.format(nowTime);
//        System.out.println(strNowTime);


        Date dateBean = applicationContext.getBean("dateBean", Date.class);
        System.out.println(dateBean);

//        Object nowTime2 = applicationContext.getBean("date");
//        System.out.println(nowTime2);
    }

    @Test
    public void testCallFunction(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserDaoImplForMySQL userDaoBean = applicationContext.getBean("userDaoBean", UserDaoImplForMySQL.class);
        userDaoBean.insert();

    }

    @Test
    public void testXmlPath(){
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("/Users/yuantengyan/IdeaProjects/Spring6/spring6-002-first/src/main/resources/xml/bean2.xml");
        Object dateBean = applicationContext.getBean("dateBean");
        System.out.println(dateBean);
    }

    @Test
    public void testBeanFactory(){
        // ApplicationContext's super class is BeanFactory
        // Spring IoC uses Factory pattern
        // parse XML + Factory pattern + reflection
        BeanFactory applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        User userBean = applicationContext.getBean("userBean", User.class);
        System.out.println(userBean);
    }

    @Test
    public void testBeginInitBean() {
        // create object when parse XML
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    }

    @Test
    public void testLog4j2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Logger logger = LoggerFactory.getLogger(FirstSpringTest.class);
        logger.info("我是一条日志消息");
        logger.debug("我是调试");
        logger.error("我是错误");
    }
}
