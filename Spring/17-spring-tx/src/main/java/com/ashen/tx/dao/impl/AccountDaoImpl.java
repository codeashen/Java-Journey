package com.ashen.tx.dao.impl;

import com.ashen.tx.dao.IAccountDao;
import com.ashen.tx.domain.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

/**
 * 账户持久层实现类
 * 继承JdbcDaoSupport，作用：
 *      避免了多个dao实现类都要写注入JdbcTemplate的重复代码
 */
public class AccountDaoImpl extends JdbcDaoSupport implements IAccountDao {

    @Override
    public Account findAccountById(Integer accountId) {
        String sql = "select * from spring_account where id = ?";
        List<Account> accounts = super.getJdbcTemplate().query(sql,
                new BeanPropertyRowMapper<Account>(Account.class), accountId);
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    @Override
    public Account findAccountByName(String accountName) {
        String sql = "select * from spring_account where name = ?";
        List<Account> accounts = super.getJdbcTemplate().query(sql,
                new BeanPropertyRowMapper<Account>(Account.class), accountName);
        if (accounts.isEmpty()) {
            return null;
        }
        if (accounts.size() > 1) {
            throw new RuntimeException("结果集不唯一");
        }
        return accounts.get(0);
    }

    @Override
    public void updateAccount(Account account) {
        String sql = "update spring_account set name = ?, money = ? where id = ?";
        super.getJdbcTemplate().update(sql, account.getName(), account.getMoney(), account.getId());
    }
}
