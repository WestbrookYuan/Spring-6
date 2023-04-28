package com.yty.bank.service.impl;

import com.yty.bank.dao.AccountDao;
import com.yty.bank.pojo.Account;
import com.yty.bank.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
@Service("accountService2")
public class AccountServiceImpl2 implements AccountService {

    @Resource(name = "accountDao")
    private AccountDao accountDao;
    @Override
    public void transfer(String fromActno, String toActno, Double money) {

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(Account account) {
        accountDao.insert(account);
        String s = null;
        s.toString();
    }
}
