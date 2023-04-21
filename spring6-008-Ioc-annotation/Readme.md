# Spring注解

### 声明Bean的注解

```java
    @AliasFor(
        annotation = Component.class
    )
```

有这个符号，证明Service，Controller，Repository都是Component的别名，提高注解的可读性

#### 在Spring中注解的使用

1. 引入Spring AOP的依赖

2. 在命名空间指定包的扫描

3. 在Bean上使用注解

   1. 如果没有指定注解的value，则会用类名首字母变小写

   ```java
   @Repository
   public class Student {
   }
    // 等同于
    // <bean id="student" class="com.yty.spring6.bean.Student"/>
   ```



#### 多个包扫描问题

1. 多个包使用逗号隔开

   ```xml
   <context:component-scan base-package="com.yty.spring6.bean, com.yty.spring6.dao"/>
   ```

2. 指定共同父包（效率降低）

   ```xml
   <context:component-scan base-package="com.yty.spring6"/>
   ```

#### 选择性实例化Bean

1. 对Component-scan添加参数use-default-filters为**false**，添加子标签**context:include-filter**

   ```xml
   <!--如果 use-default-filters是false 会使包下锁所有的声明Bean的注解失效-->
       <context:component-scan base-package="com.yty.spring6.bean2" use-default-filters="false">
   <!--        只有它会被包含进来-->
           <context:include-filter type="annotation" expression="org.springframework.stereotype.Service"/>
       </context:component-scan>
   
   ```

2. 添加子标签**exclude-filter**

   ```xml
       <context:component-scan base-package="com.yty.spring6.bean2">
           <!--        只有它会被排除出去-->
           <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Service"/>
       </context:component-scan>
   ```



### 负责注入的注解

1. **@Component @Controller @Service @Repository** 这四个注解是用来声明Bean的，声明后这些Bean将被实例化。接下来我们看一下，如何给Bean的属性赋值。给Bean属性赋值需要用到这些注解：

- @Value
- @Autowired
- @Qualifier
- @Resource

2. **@Value** 可以使用@Value注入简单数据类型

   ```java
   public class MyDataSource implements DataSource {
       @Value("com.mysql.cj.jdbc.Driver")
       private String driver;
       @Value("jdbc:mysql://localhost:3306/spring6")
       private String url;
       @Value("root")
       private String username;
       @Value("12345678")
       private String password;
   
       public void setDriver(String driver) {
           this.driver = driver;
       }
   
       public void setUrl(String url) {
           this.url = url;
       }
   
       public void setUsername(String username) {
           this.username = username;
       }
   
       public void setPassword(String password) {
           this.password = password;
       }
   
       @Override
       public String toString() {
           return "MyDataSource{" +
                   "driver='" + driver + '\'' +
                   ", url='" + url + '\'' +
                   ", username='" + username + '\'' +
                   ", password='" + password + '\'' +
                   '}';
       }
   }
   ```

   1. 使用Value注解不需要提供set方法

      ```java
      package com.yty.spring6.bean3;
      
      import org.springframework.beans.factory.annotation.Value;
      import org.springframework.stereotype.Component;
      
      /**
       * @author yty
       * @version 1.0
       * @since 1.0
       **/
      @Component
      public class Product {
      		@Value("wjs")
          private String realName;
      		@Value("wjs")
          private int age;
      
      
          @Override
          public String toString() {
              return "User{" +
                      "realName='" + realName + '\'' +
                      ", age=" + age +
                      '}';
          }
      }
      
      ```

   2. 可以用在方法中

      ```java
      package com.yty.spring6.bean3;
      
      import org.springframework.beans.factory.annotation.Value;
      import org.springframework.stereotype.Component;
      
      /**
       * @author yty
       * @version 1.0
       * @since 1.0
       **/
      @Component
      public class Product {
      
          private String realName;
      
          private int age;
      
          @Value("24")
          public void setAge(int age) {
              this.age = age;
          }
      
          @Value("wjs")
          public void setName(String realName) {
              this.realName = realName;
          }
      
          @Override
          public String toString() {
              return "User{" +
                      "realName='" + realName + '\'' +
                      ", age=" + age +
                      '}';
          }
      }
      
      ```

      

   3. 也可以使用在构造方法上

      ```java
      package com.yty.spring6.bean3;
      
      import org.springframework.beans.factory.annotation.Value;
      import org.springframework.stereotype.Component;
      
      /**
       * @author yty
       * @version 1.0
       * @since 1.0
       **/
      @Component
      public class Product {
          private String realName;
          private int age;
      
          public Product(@Value("wjs") String realName,@Value("24") int age) {
              this.realName = realName;
              this.age = age;
          }
      
          @Override
          public String toString() {
              return "User{" +
                      "realName='" + realName + '\'' +
                      ", age=" + age +
                      '}';
          }
      }
      
      ```

3. **@Autowire和@Qualifier**

   1. @Autowire可以用于注入非简单类型，单独使用@Autowired，默认是根据类型装配（ByType）

   2. 想根据ByName装配，需要联合使用@Autowire和@Qualifier

   3. @Autowired可以出现的位置：变量、set方法、形参、构造方法、甚至可以省略（只有一个成员变量和只有一个对应的构造方法）

      ```java
      package com.yty.service;
      
      import com.yty.dao.OrderDao;
      import org.springframework.beans.factory.annotation.Autowired;
      import org.springframework.beans.factory.annotation.Qualifier;
      import org.springframework.stereotype.Service;
      
      /**
       * @author yty
       * @version 1.0
       * @since 1.0
       **/
      @Service
      public class OrderService {
          //不需要任何属性，直接使用这个注解即可
          //根据ByType进行自动装配
          // @Autowired
          private OrderDao orderDao;
      
          //@Autowired
          public void setOrderDao(OrderDao orderDao) {
              this.orderDao = orderDao;
          }
          // @Autowired
      /*    public OrderService(@Autowired OrderDao orderDao) {
              this.orderDao = orderDao;
          }*/
      
          public OrderService(OrderDao orderDao) {
              this.orderDao = orderDao;
          }
      
          public void generate(){
              orderDao.insert();
          }
      
      }
      
      ```

4. **@Resource**

   1. @Resource注解也可以完成非简单类型注入。那它和@Autowired注解有什么区别？
      1. @Resource注解是JDK扩展包中的，也就是说属于JDK的一部分。所以该注解是标准注解，更加具有通用性。(JSR-250标准中制定的注解类型。JSR是Java规范提案。)
      2. @Autowired注解是Spring框架自己的。
      3. **@Resource注解默认根据名称装配byName，未指定name时，使用属性名作为name。通过name找不到的话会自动启动通过类型byType装配。**
      4. **@Autowired注解默认根据类型装配byType，如果想根据名称装配，需要配合@Qualifier注解一起用。**
      5. @Resource注解用在属性上、setter方法上。
      6. @Autowired注解用在属性上、setter方法上、构造方法上、构造方法参数上。

   @Resource注解属于JDK扩展包，所以不在JDK当中，需要额外引入以下依赖：【**如果是JDK8的话不需要额外引入依赖。高于JDK11或低于JDK8需要引入以下依赖。**】

   ```xml
   <dependency>
     <groupId>jakarta.annotation</groupId>
     <artifactId>jakarta.annotation-api</artifactId>
     <version>2.1.1</version>
   </dependency>
   
   
   ```

5. 全注解式开发：

   1. 不再使用配置文件，使用一个配置类

      ```java
      package com.powernode.spring6.config;
      
      import org.springframework.context.annotation.ComponentScan;
      import org.springframework.context.annotation.ComponentScans;
      import org.springframework.context.annotation.Configuration;
      
      @Configuration
      @ComponentScan({"com.powernode.spring6.dao", "com.powernode.spring6.service"})
      public class Spring6Configuration {
      }
      ```

      ```java
      @Test
      public void testNoXml(){
          ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Spring6Configuration.class);
          UserService userService = applicationContext.getBean("userService", UserService.class);
          userService.save();
      }
      ```

      