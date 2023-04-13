package com.yty.spring6.test;

import com.yty.spring6.bean.Student;
import com.yty.spring6.bean.User;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class TestBeanLifecycle {

    @Test
    public void beanLifecycle5Step(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        User userBean = applicationContext.getBean("userBean", User.class);
        System.out.println("8.使用Bean");
        // 必须手动关闭spring容器，Spring容器才能销毁Bean
        ClassPathXmlApplicationContext context = (ClassPathXmlApplicationContext) applicationContext;
        context.close();
    }

    @Test
    public void testRegisterBean(){
        Student student = new Student();
        System.out.println(student);
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerSingleton("studentBean", student);
        Student studentBean = factory.getBean("studentBean", Student.class);
        System.out.println(studentBean);
        System.out.println(studentBean.equals(student));
    }
}
