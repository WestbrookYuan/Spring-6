# Spring AOP 注解形式

### AOP的七大术语

1. 连接点 JoinPoint：可以织入切面的**位置**
   1. 方法前后
   2. 异常
   3. 最终
2. 切点PointCut：真正织入切面的方法
3. 通知advice：又叫增强，具体要植入的代码
   1. 前置通知：方法前
   2. 后置通知：方法后
   3. 环绕通知：方法前后
   4. 异常通知：异常中
   5. 最终通知：finally中
4. 切面：切点+通知
5. 织入：把通知应用到目标方法的过程
6. 代理对象
7. 目标对象

### 切点表达式

用来定义通知往哪些方法上切入

**切点表达式语法格式**：

```
execution([访问修饰符] 返回值类型 [全限定包名] [方法名] （形参列表）[异常])
```

访问控制权限修饰符：

- 可选项。
- 没写，就是4个权限都包括。
- 写public就表示只包括公开的方法。

返回值类型：

- 必填项。
- \* 表示返回值类型任意。

全限定类名：

- 可选项。
- 两个点“..”代表当前包以及子包下的所有类。
- 省略时表示所有的类。

方法名：

- 必填项。
- *表示所有方法。
- set*表示所有的set方法。

形式参数列表：

- 必填项

- () 表示没有参数的方法
- (..) 参数类型和个数随意的方法
- (*) 只有一个参数的方法
- (*, String) 第一个参数类型随意，第二个参数是String的。

异常：

- 可选项。
- 省略时表示任意异常类型。

### 通知类型实例及执行顺序

#### 使用注解形式

1. 预处理：

   ```xml
   <repositories>
           <repository>
               <id>repository.spring.milestone</id>
               <name>Spring Milestone Repository</name>
               <url>https://repo.spring.io/milestone</url>
           </repository>
       </repositories>
       <dependencies>
           <dependency>
               <groupId>org.springframework</groupId>
               <artifactId>spring-context</artifactId>
               <version>6.0.0-M2</version>
           </dependency>
           <dependency>
               <groupId>org.springframework</groupId>
               <artifactId>spring-aop</artifactId>
               <version>6.0.0-M2</version>
           </dependency>
           <dependency>
               <groupId>org.springframework</groupId>
               <artifactId>spring-aspects</artifactId>
               <version>6.0.0-M2</version>
           </dependency>
           <dependency>
               <groupId>junit</groupId>
               <artifactId>junit</artifactId>
               <version>4.13.2</version>
               <scope>test</scope>
           </dependency>
       </dependencies>
   ```

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xmlns:aop="http://www.springframework.org/schema/aop"
          xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                              http://www.springframework.org/schema/context  http://www.springframework.org/schema/context/spring-context.xsd
                              http://www.springframework.org/schema/aop  http://www.springframework.org/schema/aop/spring-aop.xsd">
   
       <context:component-scan base-package="com.yty.spring6.service com.yty.spring6.aspect"/>
   
   <!--    开启自动代理
           查看该类是否有@Aspect注解，如果有，就给这个类生成代理对象
           proxy-target-class="" 表示强制使用cglib动态代理
           false是默认值 表示使用jdk
           -->
       <aop:aspectj-autoproxy/>
   </beans>
   ```

   ```java
   package com.yty.spring6.service;
   
   import org.springframework.stereotype.Component;
   import org.springframework.stereotype.Service;
   
   /**
    * @author yty
    * @version 1.0
    * @since 1.0
    **/
   @Service
   public class OrderService {
       public void generate(){
           System.out.println("generate");
       }
   }
   
   
   ```

2. 编写Aspect类

   ```java
   package com.yty.spring6.aspect;
   
   import org.aspectj.lang.ProceedingJoinPoint;
   import org.aspectj.lang.annotation.*;
   import org.springframework.stereotype.Component;
   
   /**
    * @author yty
    * @version 1.0
    * @since 1.0
    **/
   
   @Component
   @Aspect
   public class LogAspect {
       /*
        * 切面 = 切点 + 通知
        * 通知就是增强，以方法的形式出现
        */
       // 前置通知
       @Before("execution(* com.yty.spring6.service..* (..))")
       public void beforeAdvice(){
           System.out.println("前置通知");
       }
       // 后置通知
       @AfterReturning("execution(* com.yty.spring6.service..* (..))")
       public void afterReturningAdvice(){
           System.out.println("后置通知");
       }
       // 环绕通知
       @Around("execution(* com.yty.spring6.service..* (..))")
       public void aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
           //前环绕
           System.out.println("前环绕");
           // 目标对象的目标方法
           joinPoint.proceed();
           //后环绕
           System.out.println("后环绕");
       }
   
       @After("execution(* com.yty.spring6.service..* (..))")
       public void afterAdvice() throws Throwable {
           System.out.println("最终通知");
       }
   
   
   }
   
      /*	
      		前环绕
   			I'm before enhance
   			generate
   			I'm before enhance
   			后环绕
   	 */
   
   /*
       前环绕
       前置通知
       generate
       异常通知
       最终通知
   
       java.lang.RuntimeException: runtime exception
   */
   ```

   1. @Before注解，加入切点表达式，成为前置通知
   2. @AfterReturning注解，加入切点表达式，成为后置通知
   3. @Around注解，加入表达式，形参中加入**ProceedingJoinPoint joinPoint** 成为环绕通知，**范围最大**
   4. @AfterThrowing注解，加入表达式，成为异常通知，此时，环绕通知中的后环绕通知将不再生成，但最终通知还会生成
   5. @After注解，加入表达式，成为最终通知



#### 切面执行顺序

1. @Order注解，数字越小优先级越高



### 通用切点

```java
@Pointcut("execution(* com.yty.spring6.service..* (..))")
    public void generalAspect(){
        /* 只是一个 标记，方法名随意，也不需要写任何代码
         *
         */
    }
    // 前置通知
    @Before("generalAspect()")
    public void beforeAdvice(){
        System.out.println("前置通知");
    }
```

### 连接点

通知方法中都可以加入连接点作为形参，可以通过joinPoint.getSignature()获得连接点的签名，通过签名获取到一个方法的具体信息

```java
    @Before("generalAspect()")
    public void beforeAdvice(JoinPoint joinPoint){
        System.out.println("前置通知");
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        System.out.println("method name: " + name);
    }
```

