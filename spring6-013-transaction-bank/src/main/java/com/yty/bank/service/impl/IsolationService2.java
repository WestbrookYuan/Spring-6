package com.yty.bank.service.impl;

import com.yty.bank.dao.AccountDao;
import com.yty.bank.pojo.Account;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
@Service("i2")
public class IsolationService2 {
    @Resource(name = "accountDao")
    private AccountDao accountDao;

    @Transactional(readOnly = true)
    public void insert(Account account){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        accountDao.insert(account);

    }
}
