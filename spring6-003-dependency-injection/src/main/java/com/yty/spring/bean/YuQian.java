package com.yty.spring.bean;

import java.util.Arrays;

public class YuQian {
    private String[] hobbies;

    public Woman[] getWomenFriends() {
        return womenFriends;
    }

    public void setWomenFriends(Woman[] womenFriends) {
        this.womenFriends = womenFriends;
    }

    private Woman[] womenFriends;

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "YuQian{" +
                "hobbies=" + Arrays.toString(hobbies) +
                ", womenFriends=" + Arrays.toString(womenFriends) +
                '}';
    }
}
