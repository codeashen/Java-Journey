package com.ashen.jdbctemplate.test;

import com.ashen.jdbctemplate.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * JdbcTemplate的CRUD操作
 */
public class JdbcTemplateDemo2 {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);

        // 1.保存
        template.update("insert into spring_account(name, money) values(?, ?)", "eee", "2000");
        // 2.删除
        template.update("delete from spring_account where name = ?", "eee");
        // 3.更新
        template.update("update spring_account set money = ? where id = ?", 1500f, 1);

        // 4.查询集合
        // 查询方法需要指定属性封装策略，即RowMapper
        String querySql = "select * from spring_account where money > ?";
        // 使用自定义RowMapper
        List<Account> accounts = template.query(querySql, new AccountRowMapper(), 500f);
        // 使用RowMapper实现类，BeanPropertyRowMapper<T>(Class)
        List<Account> accounts1 = template.query(querySql, new BeanPropertyRowMapper<Account>(Account.class), 500f);
        for (Account account : accounts) {
            System.out.println(account);
        }

        // 5.查询一个
        Long count = template.queryForObject("select count(1) from spring_account where money > ?",
                Long.class, 500f);
        System.out.println(count + "个账户资产超过500");

    }
}

/**
 * 自定义JdbcTemplate中的RowMapper实现类
 * 自定义Account的属性封装策略
 */
class AccountRowMapper implements RowMapper {

    /**
     * 把结果集中的数据封装到Account中，然后由spring把每个Account加到集合中
     * @param rs 查询的结果集
     * @param i 行号rowNum
     * @return
     * @throws SQLException
     */
    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setName(rs.getString("name"));
        account.setMoney(rs.getFloat("money"));
        return account;
    }
}
