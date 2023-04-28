package com.yty.bank.dao;

import com.yty.bank.pojo.Account;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public interface AccountDao {
    Account selectByActno(String actno);
    int update(Account account);
    int insert(Account account);
}
