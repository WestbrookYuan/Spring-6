package com.yty.spring6.test;

import cn.yty.Spring6Config;
import cn.yty.service.StudentService;
import com.yty.service.OrderService;
import com.yty.spring6.bean.Order;
import com.yty.spring6.bean.Student;
import com.yty.spring6.bean.User;
import com.yty.spring6.bean3.Product;
import com.yty.spring6.bean.Vip;
import com.yty.spring6.bean3.MyDataSource;
import com.yty.spring6.dao.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class IocAnnotationTest {
    @Test
    public void testBeanComponent(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        User userBean = applicationContext.getBean("userBean", User.class);
        Vip vipBean = applicationContext.getBean("vipBean", Vip.class);
        Order orderBean = applicationContext.getBean("order", Order.class);
        Student studentBean = applicationContext.getBean("student", Student.class);
        System.out.println(userBean);
        System.out.println(vipBean);
        System.out.println(orderBean);
        System.out.println(studentBean);

        UserDao userDao = applicationContext.getBean("userDao", UserDao.class);
        System.out.println(userDao);

    }

    @Test
    public void testChoose(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-choose.xml");
    }

    @Test
    public void testDIByAnnotation(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-di-annotation.xml");
        MyDataSource myDataSource = applicationContext.getBean("myDataSource", MyDataSource.class);
        System.out.println(myDataSource);
        Product product = applicationContext.getBean("product", Product.class);
        System.out.println(product);
    }

    @Test
    public void testAutowired(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-autowired.xml");
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        orderService.generate();
    }

    @Test
    public void testResource(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-resource.xml");
        StudentService studentService = applicationContext.getBean("studentService", StudentService.class);
        studentService.deleteStudent();
    }

    @Test
    public void tetNoXML(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Spring6Config.class);
        StudentService studentService = applicationContext.getBean("studentService", StudentService.class);
        studentService.deleteStudent();
    }
}
