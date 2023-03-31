# Spring依赖注入

service调用Dao的方法，此时由于我们试图实现依赖倒置原则（DIP），使用Spring框架执行IoC，必须使用依赖注入

### Set注入

1. 在service中实现一个set方法
2. 想让spring调用set方法，要在service的bean配置中加入子标签， name="userDao"，调用的是Set方法而**不是**类名

```xml

    <bean class="com.yty.spring.dao.Impl.MySQLDaoImpl" id="userDaoBean"/>
    <bean class="com.yty.spring.service.Impl.UserServiceImpl" id="userServiceBean">
        <!-- configure property child tag
             name set method without "set" and lower first letter
             SetUserDao to userDao
             ref: id of the Dao bean-->
        <property name="userDao" ref="userDaoBean"/>
    </bean>
```

### 构造注入

1. 调用构造方法

```xml
    <bean id="MySQLDaoBean" class="com.yty.spring.dao.Impl.MySQLDaoImpl"/>
    <bean id="OracleDaoBean" class="com.yty.spring.dao.Impl.OracleDaoImpl"/>
    <bean id="customerServiceBean" class="com.yty.spring.service.Impl.CustomerServiceImpl">
        <!--constructor injection
            index: constructor param index
            ref: bean id-->
        <constructor-arg index="0" ref="MySQLDaoBean"/>
        <constructor-arg index="1" ref="OracleDaoBean"/>
    </bean>

<!-- or -->

    <bean id="customerServiceBean2" class="com.yty.spring.service.Impl.CustomerServiceImpl">
        <!--constructor injection
            name: attribute name in construct method(can be finished IDEA)
            ref: bean id-->
        <constructor-arg name="mySQLDao" ref="MySQLDaoBean"/>
        <constructor-arg name="oracleDao" ref="OracleDaoBean"/>
    </bean>

<!-- or -->
    <bean id="customerServiceBean3" class="com.yty.spring.service.Impl.CustomerServiceImpl">
        <!--constructor injection
            can be used without name or id
            ref: bean id-->
        <constructor-arg ref="MySQLDaoBean"/>
        <constructor-arg  ref="OracleDaoBean"/>
    </bean>
```

2. 

### Set注入的小细节

1. 内部Bean和外部Bean

   ```xml
       <!--define a bean-->
       <bean id="orderDaoBean" class="com.yty.spring.dao.OrderDao"/>
   
       <!--if using ref: outside Bean-->
       <bean id="orderServiceBean" class="com.yty.spring.service.OrderService">
           <property name="orderDao" ref="orderDaoBean"/>
       </bean>
   
       <!--inside Bean-->
       <bean id="orderServiceBean2" class="com.yty.spring.service.OrderService">
       <!--property can include bean tag to use inside bean-->
           <property name="orderDao">
               <bean class="com.yty.spring.dao.OrderDao"/>
           </property>
       </bean>
   ```

2. 注入简单类型

   1. 使用value attribute

      ```xml
          <!--basic data type set inject-->
          <bean id="userBean" class="com.yty.spring.bean.User">
              <!--use value attribute-->
              <property name="name" value="zhangsan"/>
              <property name="age" value="15"/>
              <property name="password" value="12345678"/>
          </bean>
      ```

   2. Spring认为的简单类型：

      1. BeanUtils类中的isSimple方法

         ```java
                 return Void.class != type && Void.TYPE != type && (ClassUtils.isPrimitiveOrWrapper(type) || Enum.class.isAssignableFrom(type) || CharSequence.class.isAssignableFrom(type) || Number.class.isAssignableFrom(type) || Date.class.isAssignableFrom(type) || Temporal.class.isAssignableFrom(type) || URI.class == type || URL.class == type || Locale.class == type || Class.class == type);
         
         ```

      2. isPrimitiveOrWrapper，是否是包装类或基本数据类型

      3. Enum、CharSequencNumber、Date、Termporal(时区)、Number、Date、URI、URL、Locale(语言)

      4. Date类型的**特殊处理**

         1. 无法直接把字符串转为Date（如果看成简单类型）
         2. 标准格式可以：Fri Mar 31 11:06:39 CDT 2023
         3. 所以实际开发过程中通常认为date不是简单类型

3. 经典应用：

   1. Java.sql.datasource

4. 级联注入

