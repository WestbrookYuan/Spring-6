package com.yty.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.String;
/**
 * custom annotation
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
// 标注注解的注解，称为元注解，@Target标注了@Component可以出现的位置
// 可以出现在类（TYPE）上和属性上（FIELD）
//@Target(value = {ElementType.FIELD, ElementType.TYPE})
// value可以省略
//@Target({ElementType.FIELD, ElementType.TYPE})
// 如果注解值是数组，如果数组中只有一个值，大括号可以省略
@Target(ElementType.TYPE)
//@Retention也是元注解，用来标注@Component注解最终保留在Class文件中，并且可以被反射机制读取
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {

    // 定义注解的属性

    /**
     * 属性类型是String
     * 属性名是value
     */
    String value();
//    String name();
//    /**
//     * 属性类型是String[]
//     * 属性名是names
//     */
//    String[] names();

}
