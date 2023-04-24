package com.yty.spring6.biz;

import org.springframework.stereotype.Service;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
@Service
public class VipService {
    public void saveVip(){
        System.out.println("saving Vip");
    }
    public void deleteVip(){
        System.out.println("deleting Vip");
    }
    public void updateVip(){
        System.out.println("updating Vip");
    }
    public void searchVip() {
        System.out.println("searching Vip");
    }
}
