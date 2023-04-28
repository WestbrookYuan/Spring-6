package com.yty.bank.pojo;

/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class Account {
    private String actNo;
    private Double balance;

    public Account() {
    }

    public Account(String actNo, Double balance) {
        this.actNo = actNo;
        this.balance = balance;
    }

    public String getActNo() {
        return actNo;
    }

    public void setActNo(String actNo) {
        this.actNo = actNo;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "actNo='" + actNo + '\'' +
                ", balance=" + balance +
                '}';
    }
}
