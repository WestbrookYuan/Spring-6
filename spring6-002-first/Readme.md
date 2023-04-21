# Spring的第一个程序

### Spring的安装配置

```xml
    <repositories>
        <repository>
            <id>repository.spring.milestone</id>
            <realName>Spring Milestone Repository</realName>
            <url>https://repo.spring.io/milestone</url>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>6.0.0-M2</version>
        </dependency>
    </dependencies>
```

### 根目录下放置Spring的配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--spring configuration-->
    <bean id="userBean" class="com.yty.spring.bean.User"/>
</beans>
```

1. 可以任意取名，放在类的根目录路径下
2. 创建完类后，需要在配置文件中配置bean：
   1. id：唯一的标识
   2. class：全名称限定符

### Spring程序的细节

1. 重复Bean id问题：报错 Configuration problem: **Bean realName 'userBean' is already used in this <beans> element**
   **Offending resource: class path resource [spring.xml]**

2. 如果id不存在：会报错返回异常：**No bean named 'date' available**

3. 通过反射机制调用类的无参构造方法创建对象:

   ```java
   public class User {
       public User(){
           System.out.println("yeah");
       }
   }
   
       @Test
       public void firstSpringTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
           //get spring beanFactory object
           // ApplicationContext is an interface, has many abstarct methods
           // use ClassPathXmlApplicationContext can load spring config from resource folder
           ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
           //get a bean
           Object userBean = applicationContext.getBean("userBean");
           System.out.println(userBean);
           Object userDaoBean = applicationContext.getBean("userDaoBean");
           System.out.println(userDaoBean);
   //        Class<?> aClass = Class.forName("com.yty.spring.bean.User");
   //        Object o = aClass.newInstance();
   //        System.out.println(o);
   
       }
   
   /** yeah
   com.yty.spring.bean.User@50a638b5
   com.yty.spring.dao.UserDaoImplForMySQL@1817d444
   **/
   
   ```

4. 可以有多个配置文件且名称不限，可以在某个文件夹下

5. 底层使用Map存放对象：key是bean的id，value是对象

6. 指定返回类型：

   ```java
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
   }
   
   ```

### 在spring中启用log4j2（Spring6之后）

```xml
        <!--log4j2的依赖-->
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>2.19.0</version>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-slf4j2-impl</artifactId>
            <version>2.19.0</version>
        </dependency>
```

```
<!--必须叫log4j2.xml, 必须在类的根目录下-->

<?xml version="1.0" encoding="UTF-8"?>

<configuration>

    <loggers>
        <!--
            level指定日志级别，从低到高的优先级：
                ALL < TRACE < DEBUG < INFO < WARN < ERROR < FATAL < OFF
        -->
        <root level="DEBUG">
            <appender-ref ref="spring6log"/>
        </root>
    </loggers>

    <appenders>
        <!--输出日志信息到控制台-->
        <console realName="spring6log" target="SYSTEM_OUT">
            <!--控制日志输出的格式-->
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss SSS} [%t] %-3level %logger{1024} - %msg%n"/>
        </console>
    </appenders>

</configuration>
```

