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

