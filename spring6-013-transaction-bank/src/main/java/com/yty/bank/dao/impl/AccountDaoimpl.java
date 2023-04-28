package com.yty.bank.dao.impl;

import com.yty.bank.dao.AccountDao;
import com.yty.bank.pojo.Account;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
@Repository("accountDao")
public class AccountDaoimpl implements AccountDao {
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public Account selectByActno(String actno) {
        String sql = "select act_no, balance from t_act where act_no=?";
        Account account = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Account.class), actno);
        return account;
    }

    @Override
    public int update(Account account) {
        String sql = "update t_act set balance=? where act_no=?";
        int count = jdbcTemplate.update(sql, account.getBalance(), account.getActNo());
        return count;
    }

    @Override
    public int insert(Account account) {
        String sql = "insert t_act values(?, ?)";
        int count = jdbcTemplate.update(sql, account.getActNo(), account.getBalance());
        return count;
    }
}
