package com.ashen.param.domain;

import java.util.List;
import java.util.Map;

/**
 * 银行实体类，有集合属性
 */
public class Bank {

    private String bankName;
    private List<Account> accounts;
    private Map<String, Person> members;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Map<String, Person> getMembers() {
        return members;
    }

    public void setMembers(Map<String, Person> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "bankName='" + bankName + '\'' +
                ", accounts=" + accounts +
                ", members=" + members +
                '}';
    }
}
