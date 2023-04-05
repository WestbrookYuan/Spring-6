package com.yty.spring6.test;

import com.yty.spring6.bean.SpringBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanScopeTest {
    @Test
    public void testBeanScope(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-scope.xml");
        SpringBean springBean1 = applicationContext.getBean("springBean", SpringBean.class);
        System.out.println(springBean1);

        SpringBean springBean2 = applicationContext.getBean("springBean", SpringBean.class);
        System.out.println(springBean2);

        SpringBean springBean3 = applicationContext.getBean("springBean", SpringBean.class);
        System.out.println(springBean3);
    }

    @Test
    public void testThreadScope(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-scope.xml");
        SpringBean springBean = applicationContext.getBean("springBean", SpringBean.class);
        System.out.println(springBean);

        SpringBean springBean1 = applicationContext.getBean("springBean", SpringBean.class);
        System.out.println(springBean1);
        Thread thread = new Thread(() -> {
            SpringBean springBean2 = applicationContext.getBean("springBean", SpringBean.class);
            System.out.println(springBean2);

            SpringBean springBean3 = applicationContext.getBean("springBean", SpringBean.class);
            System.out.println(springBean3);
        });
        thread.start();
    }

}
