# 注解回顾

```java
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
//    String realName();
//    /**
//     * 属性类型是String[]
//     * 属性名是names
//     */
//    String[] names();

}
```



### 实战需求：IoC组件读取注解的原理：

```java
package com.yty.client;

import com.yty.annotation.Component;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class ComponentScan {
    public static void main(String[] args) throws Exception{
        Map<String, Object> beanMap = new HashMap<>();
        String packageName = "com.yty.bean";
        // .表示任意字符，必须是一个普通的.字符，使用反斜杠\\.
        String packagePath = packageName.replaceAll("\\.", "/");
        System.out.println(packagePath);
        URL url = ClassLoader.getSystemClassLoader().getResource(packagePath);
        String path = url.getPath();
        File file = new File(path);
        File[] files = file.listFiles();
        Arrays.stream(files).forEach(f -> {
            //System.out.println(f.getName().split("\\.")[0]);
            String className = packageName +"." +f.getName().split("\\.")[0];
            System.out.println(className);
            Class<?> aClass = null;
            try {
                aClass = Class.forName(className);
                if (aClass.isAnnotationPresent(Component.class)){
                    Component annotation = aClass.getAnnotation(Component.class);
                    String id = annotation.value();
                    Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
                    Object o = declaredConstructor.newInstance();
                    beanMap.put(id, o);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        System.out.println(beanMap);
    }
}

```

