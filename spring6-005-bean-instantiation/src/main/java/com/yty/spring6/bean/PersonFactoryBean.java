package com.yty.spring6.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class PersonFactoryBean implements FactoryBean<Person> {
    @Override
    public Person getObject() throws Exception {
        return new Person();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
