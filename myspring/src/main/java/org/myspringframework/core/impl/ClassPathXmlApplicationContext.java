package org.myspringframework.core.impl;

import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.myspringframework.core.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class ClassPathXmlApplicationContext implements ApplicationContext {
    private final static Logger logger = LoggerFactory.getLogger(ClassPathXmlApplicationContext.class);
    private Map<String, Object> singletonObjects = new HashMap<>();
    /**
     * 解析配置文件，初始化所有的Bean对象，
     * @param configureLocation spring文件的配置路径，使用ClassPathXmlApplicationContext,配置文件应当放到类目录下
     */
    public ClassPathXmlApplicationContext(String configureLocation){
        /**
         * 解析配置文件，实例化Bean，将Bean放入Map singletonObjects中
         */

        try {
            SAXReader reader = new SAXReader();
            InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(configureLocation);
            Document document = reader.read(in);
            // xml文件读取多个同类标签，selectNodes()方法，两个斜杠+标签名
            List<Node> nodes = document.selectNodes("//bean");
            // 将对象实例化后曝光
            nodes.forEach(node -> {
                try{
                    //System.out.println(node);
                    Element beanElt = (Element) node;
                    String beanId = beanElt.attributeValue("id");
                    String className = beanElt.attributeValue("class");
                    logger.info("beanid=" + beanId);
                    logger.info("className=" + className);
                    // get class
                    Class<?> aClass = Class.forName(className);
                    // get default constructor
                    Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
                    Object bean = declaredConstructor.newInstance();
                    singletonObjects.put(beanId, bean);
                    logger.info(singletonObjects.toString());
                } catch (Exception e){
                    e.printStackTrace();
                }

            });
            // 将对象注入属性
            nodes.forEach(node -> {
                try{
                    Element beanELt = (Element) node;
                    String beanId = beanELt.attributeValue("id");
                    String className = beanELt.attributeValue("class");
                    Class<?> aClass = Class.forName(className);
                    // get all properties
                    List<Element> properties = beanELt.elements("property");
                    properties.forEach(property -> {
                        try{
                            // get property name
                            String propertyName = property.attributeValue("name");
                            // get property type
                            Field field = aClass.getDeclaredField(propertyName);
                            logger.info(propertyName);
                            // get set method name
                            String setMethodName = "set" + propertyName.toUpperCase().charAt(0) + propertyName.substring(1);
                            // get set method
                            Method setMethod = aClass.getDeclaredMethod(setMethodName, field.getType());
                            // call set method
                            String value = property.attributeValue("value");
                            Object actualVal = null;
                            String ref = property.attributeValue("ref");
                            if (value != null){
                                // simple data type
                                // only support byte short int long float double boolean char
                                //              Byte Short Integer Long Float Double Boolean Character String
                                String propertyTypeSimpleName = field.getType().getSimpleName();
                                switch (propertyTypeSimpleName){
                                    case "byte":
                                        actualVal = Byte.parseByte(value);
                                        break;
                                    case "short":
                                        actualVal = Short.parseShort(value);
                                        break;
                                    case "int":
                                        actualVal = Integer.parseInt(value);
                                        break;
                                    case "long":
                                        actualVal = Long.parseLong(value);
                                        break;
                                    case "float":
                                        actualVal = Float.parseFloat(value);
                                        break;
                                    case "double":
                                        actualVal = Double.parseDouble(value);
                                        break;
                                    case "boolean":
                                        actualVal = Boolean.parseBoolean(value);
                                        break;
                                    case "char":
                                        actualVal = value.charAt(0);
                                        break;
                                    case "Byte":
                                        actualVal = Byte.valueOf(value);
                                        break;
                                    case "Short":
                                        actualVal = Short.valueOf(value);
                                        break;
                                    case "Integer":
                                        actualVal = Integer.valueOf(value);
                                        break;
                                    case "Long":
                                        actualVal = Long.valueOf(value);
                                        break;
                                    case "Double":
                                        actualVal = Double.valueOf(value);
                                        break;
                                    case "Boolean":
                                        actualVal = Boolean.valueOf(value);
                                        break;
                                    case "Character":
                                        actualVal = Character.valueOf(value.charAt(0));
                                        break;
                                    case "String":
                                        actualVal = value;
                                        break;
                                }
                                setMethod.invoke(singletonObjects.get(beanId), actualVal);
                            }
                            if (ref != null){
                                // non simple data type
                                // bean which has been instantiated
                                setMethod.invoke(singletonObjects.get(beanId), singletonObjects.get(ref));
                            }

                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }

                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @Override
    public Object getBean(String BeanName) {
        return singletonObjects.get(BeanName);
    }
}
