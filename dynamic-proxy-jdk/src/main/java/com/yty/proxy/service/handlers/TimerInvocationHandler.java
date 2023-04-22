package com.yty.proxy.service.handlers;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 * 负责计时的调用处理器代码，增强有关计时的代码，只需要调用一个就行了
 **/
public class TimerInvocationHandler implements InvocationHandler {
    //目标对象
    private Object target;

    public TimerInvocationHandler() {
    }

    public TimerInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * JDK在底层调用invoke方法生成动态代理对象
     * @param proxy the proxy instance that the method was invoked on
     *
     * @param method the {@code Method} instance corresponding to
     * the interface method invoked on the proxy instance.  The declaring
     * class of the {@code Method} object will be the interface that
     * the method was declared in, which may be a superinterface of the
     * proxy interface that the proxy class inherits the method through.
     *
     * @param args an array of objects containing the values of the
     * arguments passed in the method invocation on the proxy instance,
     * or {@code null} if interface method takes no arguments.
     * Arguments of primitive types are wrapped in instances of the
     * appropriate primitive wrapper class, such as
     * {@code java.lang.Integer} or {@code java.lang.Boolean}.
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long begin = System.currentTimeMillis();
        Object retObj = method.invoke(target, args);
        long end = System.currentTimeMillis();
        System.out.println("time consume: "+(end - begin));
        return retObj;
    }
}
