package com.yty.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class Test4 {

    /**
     * 1. 有一个类：com.yty.reflect.User
     * 2. 符合Java Bean规范
     * 3. 类中有age
     * 4. age是int
     * 5. 使用反射机制调用相关方法，给age赋值
     */
    public static void main(String[] args) throws Exception {
        String ClazzName = "com.yty.reflect.User";
        String propertyName = "age";
        String setMethodName = "set" + propertyName.toUpperCase().charAt(0)+propertyName.substring(1);

        Class<?> aClass = Class.forName(ClazzName);
        Field field = aClass.getDeclaredField(propertyName);

        Method declaredMethod = aClass.getDeclaredMethod(setMethodName, field.getType());
        Object o = aClass.newInstance();
        declaredMethod.invoke(o, 25);

        System.out.println(o);
    }
}
