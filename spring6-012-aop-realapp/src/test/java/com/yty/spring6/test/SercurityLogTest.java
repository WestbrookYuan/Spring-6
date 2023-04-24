package com.yty.spring6.test;

import com.yty.spring6.biz.UserService;
import com.yty.spring6.biz.VipService;
import com.yty.spring6.config.Spring6Config;
import com.yty.spring6.service.AccountService;
import com.yty.spring6.service.OrderService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class SercurityLogTest {
    @Test
    public void testSecurityLog(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Spring6Config.class);
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.saveUser();
        userService.deleteUser();
        userService.searchUser();
        userService.updateUser();
        System.out.println();

        VipService vipService = applicationContext.getBean("vipService", VipService.class);
        vipService.deleteVip();
        vipService.saveVip();
        vipService.searchVip();
        vipService.updateVip();
    }
}
