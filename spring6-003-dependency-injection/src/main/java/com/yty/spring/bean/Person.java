package com.yty.spring.bean;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Person {
    private List<String> names;
    private Set<String> addresses;
    private Map<Integer, String> phones;
    private Properties properties;

    @Override
    public String toString() {
        return "Person{" +
                "names=" + names +
                ", addresses=" + addresses +
                ", phones=" + phones +
                ", properties=" + properties +
                '}';
    }



    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }


    public Map<Integer, String> getPhones() {
        return phones;
    }

    public void setPhones(Map<Integer, String> phones) {
        this.phones = phones;
    }



    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public Set<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<String> addresses) {
        this.addresses = addresses;
    }

}
