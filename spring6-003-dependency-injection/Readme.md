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

   1. 学生属于哪个班级
   2. 对于学生的clazz属性，同时要提供get方法，提取clazz之后set这个对象的名字，先set clazz对象，再set clazz内部的属性

   ```xml
       <bean id="studentBean" class="com.yty.spring.bean.Student">
           <property name="name" value="zhangsan" />
           <property name="clazz" ref="clazzBean" />
           <property name="clazz.name" value="NZ 172"/>
         <!--use getClazz()
              name="clazz.name equals to getClazz().setName()-->
       </bean>
   ```

5. 注入array

   1. array标签

      1. 简单数据类型：使用value

         ```xml
                 <property name="hobbies">
                     <array>
                         <value>smoke</value>
                         <value>drink</value>
                         <value>haircut</value>
                     </array>
                 </property>
         ```

      2. 复杂数据类型：使用ref

         ```xml
                 <property name="womenFriends">
                     <array>
                         <ref bean="woman1"/>
                         <ref bean="woman2"/>
                         <ref bean="woman3"/>
                     </array>
                 </property>
         ```

6. 注入Collection

   ```xml
       <bean id="personBean" class="com.yty.spring.bean.Person">
   
           <property name="names">
               <list>
                   <value>zhangsan</value>
                   <value>lisi</value>
                   <value>wangwu</value>
                   <value>zhangsan</value>
               </list>
           </property>
   
           <property name="addresses">
               <set>
                   <value>MO</value>
                   <value>NM</value>
                   <value>NY</value>
                   <value>NY</value>
               </set>
           </property>
           <property name="phones">
               <map>
                   <!--simple data type-->
                   <entry key="1" value="110"/>
                   <entry key="2" value="119"/>
                   <entry key="3" value="120"/>
                   <!--complex data type-->
                   <entry key-ref="" value-ref=""/>
               </map>
           </property>
         
           <property name="properties">
               <props>
                   <prop key="driver">com.mysql.cj.jdbc.Driver</prop>
                   <prop key="url">jdbc:mysql://localhost:3306/spring6</prop>
                   <prop key="user">root</prop>
                   <prop key="password">12345678</prop>
               </props>
           </property>
       </bean>
   ```

   

   1. list：有序可重复:list
   2. set：无需不可重复，会去掉xml中重复的元素:set
   3. Map:entry
   4. properties属性类:prop

7. 注入null和空串

   1. 不注入：少写一个property标签：注入一个null

   2. 手动注入null：注入一个null

      ```xml
              <property name="name">
                  <null/>
              </property>
      ```

   3. 有value属性但不放置内容：添加一个空串，可以调用String的方法

      ```xml
              <property name="name">
                  <value/>
              </property>
              
              <property name="name" value=""/>
      ```

8. 注入特殊符号

   1. 使用实体符号：

      ```xml
          <bean id="mathBean" class="com.yty.spring.bean.MathBean">
              <property name="result" value="2 &lt; 3"/>
          </bean>
      ```

   2. <![CDATA[2 < 3]\]>

9. P命名空间注入（简化set注入）

   1. 底层还是set注入，可以让配置相对简单

   2. 添加p命名空间

      ```xml
      xmlns:p="http://www.springframework.org/schema/p"
      
      ```

      ```xml
          <bean id="birthBean" class="java.util.Date"/>
          <bean id="dogBean" class="com.yty.spring.bean.Dog" p:name="Syb" p:age="25" p:birth-ref="birthBean"/>
      ```

10. C命名空间注入（简化构造注入）

    ```xml
    xmlns:c="http://www.springframework.org/schema/c"
    
        <bean id="peopleBean" class="com.yty.spring.bean.People" c:name="yty" c:sex="1" c:age="23"/>
    ```

11. util命名空间

    1. 让配置复用(如相同数据库连接不同数据源)

       ```xml
       <beans xmlns="http://www.springframework.org/schema/beans"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xmlns:util="http://www.springframework.org/schema/util"
              xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
              http://www.springframework.org/schema/util  http://www.springframework.org/schema/util/spring-util.xsd">
       ```

12. 基于XML的自动装配

    1. 调用无参构造方法和set方法

    2. byName

       1. set方法的方法名去掉set变小写

       2. autowire属性

          ```xml
          <!--    根据名字进行自动装配-->
              <bean id="orderDao" class="com.yty.spring.dao.OrderDao"/>
              <bean id="orderService" class="com.yty.spring.service.OrderService" autowire="byName"/>
          ```

    3. byType

       1. 某种类型的实例只能有一个

          ```xml
              <bean class="com.yty.spring.dao.Impl.MySQLDaoImpl"/>
              <bean class="com.yty.spring.dao.Impl.OracleDaoImpl"/>
              <bean id="customService" class="com.yty.spring.service.Impl.CustomerServiceImpl" autowire="byType"/>
          ```

       2. 




