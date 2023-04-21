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
