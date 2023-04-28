package com.yty.bank.mapper;

import com.yty.bank.pojo.Account;

import java.util.List;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public interface AccountMapper {

    int insert(Account account);
    int deleteByActno(String actno);
    int updateByActno(Account account);
    Account selectByActno(String actno);
    List<Account> SelectAll();
}
