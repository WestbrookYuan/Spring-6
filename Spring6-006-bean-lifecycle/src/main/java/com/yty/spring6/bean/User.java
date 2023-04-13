package com.yty.spring6.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/** Bean的生命周期：
 *  1. 实例化bean：调用无参构造
 *  2. 给Bean赋值：set方法
 *  3. 初始化Bean：调用Bean的init方法（自己写
 *  4. 使用Bean
 *  5. 销毁Bean：调用destroy方法（自己写
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class User implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, InitializingBean,DisposableBean {
    private String name;
    public User(){
        System.out.println("1.无参构造");
    }

    public void setName(String name) {
        System.out.println("2.给Bean赋值");
        this.name = name;
    }
    public void initBean(){
        System.out.println("6.初始化bean");
    }

    public void destroyBean(){
        System.out.println("10.销毁Bean");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("3.bean的类加载器："+ classLoader);
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("3.这个Bean的名字是："+s);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("3.生产这个Bean的factory是："+beanFactory);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("5.Initializing bean 方法执行");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("9.DisposableBean接口方法执行");
    }
}
