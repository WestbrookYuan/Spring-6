package com.yty.spring6.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
@Component
@Aspect
public class SecurityLogAspect {
    @Pointcut("execution(* com.yty.spring6.biz..save* (..))")
    public void savePointCut(){}
    @Pointcut("execution(* com.yty.spring6.biz..delete* (..))")
    public void deletePointCut(){}
    @Pointcut("execution(* com.yty.spring6.biz..update* (..))")
    public void updatePointCut(){}
    @Pointcut("execution(* com.yty.spring6.biz..search* (..))")
    public void searchPointCut(){}
    @Before("savePointCut() || deletePointCut() || updatePointCut()")
    public void beforeAdvice(JoinPoint joinPoint){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String nowTime = sdf.format(new Date());
        System.out.println(nowTime +" " + joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
    }
}
