package com.yty.bank.service;

import com.yty.bank.pojo.Account;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public interface AccountService {
    void transfer(String fromActno, String toActno, Double money);
}
