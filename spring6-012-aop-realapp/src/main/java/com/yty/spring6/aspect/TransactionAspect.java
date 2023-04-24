package com.yty.spring6.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
@Component
@Aspect
public class TransactionAspect {

    @Pointcut("execution(* com.yty.spring6.service..*(..))")
    public void generalPointCut(){
    }
    @Around("generalPointCut()")
    public void aroundAdvice(ProceedingJoinPoint joinPoint){
        try {
            System.out.println("open transaction");
            joinPoint.proceed();
            System.out.println("commit transaction");
        }
        catch (Throwable e) {
            System.out.println("rollback transaction");
        }
    }
}
