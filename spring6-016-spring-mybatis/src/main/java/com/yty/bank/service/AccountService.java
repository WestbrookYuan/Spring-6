package com.yty.bank.service;

import com.yty.bank.pojo.Account;

import java.util.List;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public interface AccountService {
    /*
    开户
     */
    int save(Account account);
    /*
    销户
     */
    int deleteByActno(String actno);
    /*
    修改
     */
    int modify(Account account);
    /*
    查询账户
     */
    Account getByActno(String actno);
    /*
    所有账户
     */
    List<Account> getAll();
    /*转账

     */
    void transfer(String fromAct, String toAct, Double money);
}
