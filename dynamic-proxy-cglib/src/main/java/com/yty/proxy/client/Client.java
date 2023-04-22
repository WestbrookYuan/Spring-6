package com.yty.proxy.client;

import com.yty.proxy.interceptor.TimerMethodInterceptor;
import com.yty.proxy.service.UserService;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class Client {
    public static void main(String[] args) {
        // 创建字节码增强器对象
        Enhancer enhancer = new Enhancer();
        // 告诉cglib目标类是谁
        enhancer.setSuperclass(UserService.class);
        // 设置回调：等同于JDK中的InvocationHandler
        // 方法拦截器接口
        enhancer.setCallback(new TimerMethodInterceptor());
        // 创建代理的对象：生成子类，创建对象
        UserService proxyObj = (UserService) enhancer.create();
        System.out.println(proxyObj);
        System.out.println(proxyObj.login("admin", "1234") ? "success" : "failed");
        proxyObj.logout();
    }


}
