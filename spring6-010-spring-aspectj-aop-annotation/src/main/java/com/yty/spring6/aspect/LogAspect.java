package com.yty.spring6.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/

@Component
@Aspect
@Order(2)
public class LogAspect {
    /*
     * 切面 = 切点 + 通知
     * 通知就是增强，以方法的形式出现
     */

    @Pointcut("execution(* com.yty.spring6.service..* (..))")
    public void generalAspect(){
        /* 只是一个 标记，方法名随意，也不需要写任何代码
         *
         */
    }
    // 前置通知
    @Before("generalAspect()")
    public void beforeAdvice(JoinPoint joinPoint){
        System.out.println("前置通知");
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        System.out.println("method name: " + name);
    }
    // 后置通知
    @AfterReturning("generalAspect()")
    public void afterReturningAdvice(){
        System.out.println("后置通知");
    }
    // 环绕通知
    @Around("generalAspect()")
    public void aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        //前环绕
        System.out.println("前环绕");
        // 目标对象的目标方法
        joinPoint.proceed();
        //后环绕
        System.out.println("后环绕");
    }

    @After("generalAspect()")
    public void afterAdvice() {
        System.out.println("最终通知");
    }
    @AfterThrowing("generalAspect()")
    public void afterThrowing() {
        System.out.println("异常通知");
    }


}
