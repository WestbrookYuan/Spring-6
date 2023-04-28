package com.yty.bank.service.impl;

import com.yty.bank.dao.AccountDao;
import com.yty.bank.pojo.Account;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
@Service("i1")
public class IsolationService1 {
    @Resource(name = "accountDao")
    private AccountDao accountDao;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void getbyActno(String actno){
        Account account = accountDao.selectByActno(actno);
        System.out.println(account);
    }
}
