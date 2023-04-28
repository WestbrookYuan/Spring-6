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
@Service("accountService")
@Transactional
public class AccountServiceimpl implements AccountService {

    @Resource(name = "accountDao")
    private AccountDao accountDao;
//    @Transactional
    @Override
    public void transfer(String fromActno, String toActno, Double money) {
        Account fromAccount = accountDao.selectByActno(fromActno);
        Account toAccount = accountDao.selectByActno(toActno);
        if (fromAccount.getBalance() < money){
            throw new RuntimeException("not enough balance");
        }

        fromAccount.setBalance(fromAccount.getBalance() - money);
        toAccount.setBalance(toAccount.getBalance() + money);

        int count = accountDao.update(fromAccount);
        String s = null;
        s.toString();
        count += accountDao.update(toAccount);
        if (count != 2){
            throw new RuntimeException("transfer failed");
        }
    }


    @Resource(name = "accountService2")
    AccountService accountService;
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Account account) {
        accountDao.insert(account);
        Account act = new Account("act-008", 500.0);
        try {
            accountService.save(act);
        }
        catch (Exception e){

        }

    }
}
