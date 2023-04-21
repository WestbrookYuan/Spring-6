# 手写Spring IOC容器

### 准备工作：

1. 引入dom4j和junit依赖

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
       <modelVersion>4.0.0</modelVersion>
   
       <groupId>org.myspringframework</groupId>
       <artifactId>myspring</artifactId>
       <version>1.0.0</version>
   
       <packaging>jar</packaging>
       <properties>
           <maven.compiler.source>17</maven.compiler.source>
           <maven.compiler.target>17</maven.compiler.target>
           <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
       </properties>
       
       <dependencies>
           <dependency>
               <groupId>org.dom4j</groupId>
               <artifactId>dom4j</artifactId>
               <version>2.1.3</version>
           </dependency>
           <dependency>
               <groupId>junit</groupId>
               <artifactId>junit</artifactId>
               <version>4.13.2</version>
               <scope>test</scope>
           </dependency>
       </dependencies>
   
   </project>
   ```

2. 作为框架使用者的角度，创建Bean类和Spring配置文件

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   
   <!--这个配置文件是由使用myspring框架的开发人员提供的-->
   <beans>
   
       <bean id="user" class="com.yty.myspring.bean.User">
           <property realName="realName" value="zhangsan"></property>
           <property realName="age" value="24"></property>
       </bean>
   
       <bean id="userDaoBean" class="com.yty.myspring.bean.UserDao">
       </bean>
   
       <bean id="userService" class="com.yty.myspring.bean.UserService">
           <property realName="userDao" ref="userDaoBean"></property>
       </bean>
   
   </beans>
   ```

   

### 核心接口实现

1. 编写ApplicationContext接口

   ```java
   package org.myspringframework.core;
   
   /** mySpring框架应用上下文
    * @author yty
    * @version 1.0
    * @since 1.0
    **/
   public interface ApplicationContext {
       /**
        * 根据bean id获取对象
        * @param BeanName
        * @return 返回对应的单例对象
        */
       Object getBean(String BeanName);
   }
   
   ```

2. 编写ApplicationContext的实现类：

   ```java
   package org.myspringframework.core.impl;
   
   import org.myspringframework.core.ApplicationContext;
   
   import java.util.HashMap;
   import java.util.Map;
   
   /**
    * @author yty
    * @version 1.0
    * @since 1.0
    **/
   public class ClassPathXmlApplicationContext implements ApplicationContext {
   
       private Map<String, Object> singletonObjects = new HashMap<>();
       /**
        * 解析配置文件，初始化所有的Bean对象，
        * @param configureLocation spring文件的配置路径，使用ClassPathXmlApplicationContext,配置文件应当放到类目录下
        */
       public ClassPathXmlApplicationContext(String configureLocation){
           /**
            * 解析配置文件，实例化Bean，将Bean放入Map singletonObjects中
            */
           Thread.currentThread().getContextClassLoader().getResourceAsStream(configureLocation);
       }
       @Override
       public Object getBean(String BeanName) {
           return singletonObjects.get(BeanName);
       }
   }
   
   ```

   1. 参考spring的三级缓存机制，确定需要一个集合存储所有实例化过后的Bean对象