# Bean的生命周期

### 五步周期

*  实例化bean：调用无参构造
*  给Bean赋值：set方法
*  初始化Bean：调用Bean的init方法（自己写
*  使用Bean
*  销毁Bean：调用destroy方法（自己写

```java
public class User {
    private String name;
    public User(){
        System.out.println("1.无参构造");
    }

    public void setName(String name) {
        System.out.println("2.给Bean赋值");
        this.name = name;
    }
    public void initBean(){
        System.out.println("3.初始化bean");
    }

    public void destroyBean(){
        System.out.println("5.销毁Bean");
    }
}


public class TestBeanLifecycle {

    @Test
    public void beanLifecycle5Step(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        User userBean = applicationContext.getBean("userBean", User.class);
        System.out.println("4 使用Bean");
        // 必须手动关闭spring容器，Spring容器才能销毁Bean
        ClassPathXmlApplicationContext context = (ClassPathXmlApplicationContext) applicationContext;
        context.close();
    }
}
```

```xml
<!--需要手动指定init、destroy方法-->    
<bean id="userBean" class="com.yty.spring6.bean.User" init-method="initBean" destroy-method="destroyBean">
        <property name="name" value="wjs"/>
    </bean>
```





### Bean生命周期7步：初始化Bean的前和后

在第三步之前和之后可以做额外操作

需要实现Bean后处理器

*  实例化bean：调用无参构造
*  给Bean赋值：set方法
*  Bean后处理器的Before方法
*  初始化Bean：调用Bean的init方法（自己写
*  Bean后处理器的After方法
*  使用Bean
*  销毁Bean：调用destroy方法（自己写

```java
package com.yty.spring6.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/** 日志Bean后处理器
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class LogBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Bean Post Processor before");
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Bean Post Processor after");
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}

```

```xml
    <!--配置Bean后处理器，将作用于配置文件中所有的Bean-->
    <bean class="com.yty.spring6.bean.LogBeanPostProcessor"/>
```



### Bean生命周期之十步：在Bean后处理器Before方法之前；Bean后处理器Before方法之后；销毁Bean之前

*  实例化bean：调用无参构造
*  给Bean赋值：set方法
*  是否实现了**Aware**的相关接口，并调用相关方法
*  Bean后处理器的Before方法
*  是否实现了**InitializingBean**的相关接口，并调用相关方法
*  初始化Bean：调用Bean的init方法（自己写
*  Bean后处理器的After方法
*  使用Bean
*  是否实现了**DisposalBean**的相关接口，并调用相关方法
*  销毁Bean：调用destroy方法（自己写

1. 在Bean后处理器Before方法之前：
   1. 检查Bean是否实现了Aware相关的接口，如果实现了则调用接口中的方法，传递一些数据方便使用

2. Bean后处理器Before方法之后：
   1. 检查是否实现了InitializingBean接口，如果实现了则调用接口中的方法

3. 销毁Bean之前：
   1. 检查是否实现了DisposableBean接口，如果实现了则调用接口中的方法



```txt
1.无参构造
2.给Bean赋值
3.这个Bean的名字是：userBean
3.bean的类加载器：jdk.internal.loader.ClassLoaders$AppClassLoader@251a69d7
3.生产这个Bean的factory是：org.springframework.beans.factory.support.DefaultListableBeanFactory@78b1cc93: defining beans [com.yty.spring6.bean.LogBeanPostProcessor#0,userBean]; root of factory hierarchy
4.Bean Post Processor before
5.Initializing bean 方法执行
6.初始化bean
7.Bean Post Processor after
8.使用Bean
9.DisposableBean接口方法执行
10.销毁Bean
```

### Spring在bean的不同作用域下不同的管理方式

1. Spring只对Singleton的Bean做完整的生命周期管理
2. 如果是prototype的Bean，spring只负责将该Bean初始化，当客户端代码获取到Spring获取的Bean之后，就不再管理，不在追踪其生命周期。



### 自己new的对象纳入Spring管理

```java
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
```



