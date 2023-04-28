package com.yty.bank.service.Impl;

import com.yty.bank.mapper.AccountMapper;
import com.yty.bank.pojo.Account;
import com.yty.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
@Transactional
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public int save(Account account) {
        return accountMapper.insert(account);
    }

    @Override
    public int deleteByActno(String actno) {
        return accountMapper.deleteByActno(actno);
    }

    @Override
    public int modify(Account account) {
        return accountMapper.updateByActno(account);
    }

    @Override
    public Account getByActno(String actno) {
        return accountMapper.selectByActno(actno);
    }

    @Override
    public List<Account> getAll() {
        return accountMapper.SelectAll();
    }

    @Override
    public void transfer(String fromAct, String toAct, Double money) {
        Account fromAccount = accountMapper.selectByActno(fromAct);
        Account toAccount = accountMapper.selectByActno(toAct);
        if (fromAccount.getBalance() < money) {
            throw new RuntimeException("余额不足");
        }
        fromAccount.setBalance(fromAccount.getBalance() - money);
        toAccount.setBalance(toAccount.getBalance() + money);
        int count = accountMapper.updateByActno(fromAccount);
        String s = null;
        s.toString();
        count += accountMapper.updateByActno(toAccount);
        if (count != 2){
            throw new RuntimeException("转账失败");
        }
    }
}
