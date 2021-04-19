package com.ashen.jdbctemplate.dao.impl;

import com.ashen.jdbctemplate.domain.Account;
import com.ashen.jdbctemplate.dao.IAccountDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户持久层实现类
 * 传统注入JdbcTemplate的方式
 */
@Repository
public class AccountDaoImpl implements IAccountDao {

    // 没法@Autowired注解自动注入，因为这个类里没有配置为Bean的注解
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account findAccountById(Integer accountId) {
        /**
         * 参数解释：
         * String sql           : sql语句
         * RowMapper rowMapper  : 行映射，用于结果集绑定
         * Object[] args        : sql预编译参数
         */
        List<Account> accounts =
                jdbcTemplate.query("select * from spring_account where id = ?",
                        new BeanPropertyRowMapper<Account>(Account.class), accountId);
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    @Override
    public Account findAccountByName(String accountName) {
        List<Account> accounts =
                jdbcTemplate.query("select * from spring_account where name = ?",
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
        jdbcTemplate.update("update spring_account set name = ?, money = ? where id = ?",
                account.getName(), account.getMoney(), account.getId());
    }
}
