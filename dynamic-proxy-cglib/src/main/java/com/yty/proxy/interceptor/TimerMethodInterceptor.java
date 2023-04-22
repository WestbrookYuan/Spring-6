package com.yty.proxy.interceptor;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class TimerMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object target, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        // 前面增强
        long begin = System.currentTimeMillis();
        // 目标方法
        Object retValue = methodProxy.invokeSuper(target, objects);
        //后面增强
        long end = System.currentTimeMillis();
        System.out.println("time consume: " + (end - begin));
        return retValue;
    }
}
