package com.ashen.tx.anno.dao.impl;

import com.ashen.tx.anno.dao.IAccountDao;
import com.ashen.tx.anno.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户持久层实现类
 */
@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Account findAccountById(Integer accountId) {
        String sql = "select * from spring_account where id = ?";
        List<Account> accounts = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<Account>(Account.class), accountId);
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    @Override
    public Account findAccountByName(String accountName) {
        String sql = "select * from spring_account where name = ?";
        List<Account> accounts = jdbcTemplate.query(sql,
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
        jdbcTemplate.update(sql, account.getName(), account.getMoney(), account.getId());
    }
}
