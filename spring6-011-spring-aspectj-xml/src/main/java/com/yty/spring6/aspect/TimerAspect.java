package com.yty.spring6.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class TimerAspect {
    // 通知
    public void aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
        long begin = System.currentTimeMillis();
        joinPoint.proceed();
        System.out.println("time consume:" + (System.currentTimeMillis() - begin));
    }
}
