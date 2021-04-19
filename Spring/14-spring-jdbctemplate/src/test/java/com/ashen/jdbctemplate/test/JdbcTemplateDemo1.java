package com.ashen.jdbctemplate.test;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * JdbcTemplate的最基本使用
 */
public class JdbcTemplateDemo1 {

    public static void main(String[] args) {
        // 准备数据源
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/demo_mybatis");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        // 创建JdbcTemplate对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        // 执行操作
        jdbcTemplate.execute("insert into spring_account(name, money) values('ddd', 1000)");
    }
}
