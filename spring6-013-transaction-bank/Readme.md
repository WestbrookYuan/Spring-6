# Spring事务管理

底层基于Spring AOP实现的，采用API的方式进行了封装：PlatformTransactionManager：两个实现

PlatformTransactionManager接口：spring事务管理器的核心接口。在**Spring6**中它有两个实现：

- DataSourceTransactionManager：支持JdbcTemplate、MyBatis、Hibernate等事务管理。
- JtaTransactionManager：支持分布式事务管理。

如果要在Spring6中使用JdbcTemplate，就要使用DataSourceTransactionManager来管理事务。（Spring内置写好了，可以直接用。）



### 注解实现方式

1. 配置事务管理器Bean
2. 加入tx命名空间
3. 告知spring使用注解方式控制事务

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context  http://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/tx   http://www.springframework.org/schema/tx/spring-tx.xsd">
    <context:property-placeholder location="jdbc.properties"/>
    <context:component-scan base-package="com.yty.bank"/>
    <bean id="druidDataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="druidDataSource"/>
    </bean>
    <!--configure transaction manager-->
    <bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="druidDataSource"/>
    </bean>
<!--    use annotation to control transaction manager-->
    <tx:annotation-driven transaction-manager="txManager"/>
</beans>
```



### @Transactional注解的属性

1. 写在类上，就是类下所有方法

2. 写在方法上，就是该方法

3. 属性

   ```java
   Propagation propagation() default Propagation.REQUIRED;
       Isolation isolation() default Isolation.DEFAULT;
       int timeout() default -1;
       boolean readOnly() default false;
   		Class<? extends Throwable>[] rollbackFor() default {};
       Class<? extends Throwable>[] noRollbackFor() default {};
   
   ```

4. 事务中的重点属性：

   - 事务传播行为：propagation

     - REQUIRED(0)：没有就新建
       - 2023-04-25 18:25:46 432 [main] DEBUG org.springframework.jdbc.datasource.DataSourceTransactionManager - **Creating new transaction with name [com.yty.bank.service.impl.AccountServiceimpl.save]:** 
         2023-04-25 18:25:46 609 [main] DEBUG org.springframework.jdbc.datasource.DataSourceTransactionManager - **Participating in existing transaction**
     - SUPPORTS(1)：没有就没有
     - MANDATORY(2)：没有就抛异常
     - REQUIRES_NEW(3)：直接开启新事务，之前事务被挂起，不存在嵌套关系：继续执行上层事务的提交
       - 2023-04-25 18:32:24 970 [main] DEBUG org.springframework.jdbc.datasource.DataSourceTransactionManager - **Creating new transaction with name [com.yty.bank.service.impl.AccountServiceimpl.save]:** PROPAGATION_REQUIRED,ISOLATION_DEFAULT
         2023-04-25 18:32:25 152 [main] DEBUG org.springframework.jdbc.datasource.DataSourceTransactionManager - **Suspending current transaction, creating new transaction with name [com.yty.bank.service.impl.AccountServiceImpl2.save]**
     - NOT_SUPPORTED(4)：不支持事务，存在就挂起
     - NEVER(5)：直接抛异常，
     - NESTED(6)：有事务就嵌套一个新事务，可以独立的提交和回滚，没有就和required一样

   - 事务隔离级别：isolation

     - 事务隔离级别类似于教室A和教室B之间的那道墙，隔离级别越高表示墙体越厚。隔音效果越好。

       数据库中读取数据存在的三大问题：（三大读问题）

       - **脏读：读取到没有提交到数据库的数据，叫做脏读。**

       - **不可重复读：在同一个事务当中，第一次和第二次读取的数据不一样。**

       - **幻读：读到的数据是假的。**脑子中想象的数据和数据库表中的数据不同

         <img src="/Users/yuantengyan/IdeaProjects/Spring-6/spring6-013-transaction-bank/截屏2023-04-25 下午10.19.59.png" alt="截屏2023-04-25 下午10.19.59" style="zoom:50%;" />

       事务隔离级别包括四个级别：

       - 读未提交：READ_UNCOMMITTED

       - - 这种隔离级别，存在脏读问题，所谓的脏读(dirty read)表示能够读取到其它事务未提交的数据。

       - 读提交：READ_COMMITTED

       - - 解决了脏读问题，其它事务提交之后才能读到，但存在不可重复读问题。

       - 可重复读：REPEATABLE_READ

       - - 解决了不可重复读，可以达到可重复读效果，只要当前事务不结束，读取到的数据一直都是一样的。但存在幻读问题。

       - 序列化：SERIALIZABLE

       - - 解决了幻读问题，事务排队执行。不支持并发。

   - 事务超时：timeout

     - **表示超过x秒如果该事务中所有的DML语句还没有执行完毕的话，最终结果会选择回滚。**

       默认值-1，表示没有时间限制。

       **这里有个坑，事务的超时时间指的是哪段时间？**

       **在当前事务当中，最后一条DML语句执行之前的时间。如果最后一条DML语句后面很有很多业务逻辑，这些业务代码执行的时间不被计入超时时间。**

   - 只读事务：readOnly

     - 将当前事务设置为只读事务，在该事务执行过程中只允许select语句执行，delete insert update均不可执行。

       该特性的作用是：**启动spring的优化策略。提高select语句执行效率。**

       如果该事务中确实没有增删改操作，建议设置为只读事务。

   - 设置出现哪些异常回滚事务：rollbackFor

     - 表示只有发生指定异常或该异常的子类异常才回滚。

   - 设置出现哪些异常不回滚事务：noRollbackFor

     - 表示发生指定异常或该异常的子类异常不回滚。

5. 



### 