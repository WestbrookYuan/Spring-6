package com.yty.spring6.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 * 安全切面
 **/
@Component
@Aspect
@Order(1)
public class SecurityAspect {
    @Before("com.yty.spring6.aspect.LogAspect.generalAspect()")
    public void beforeAdvise(){
        System.out.println("前置通知 安全");
    }
}
