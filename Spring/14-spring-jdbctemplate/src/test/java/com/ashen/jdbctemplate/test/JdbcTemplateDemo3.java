package com.ashen.jdbctemplate.test;

import com.ashen.jdbctemplate.domain.Account;
import com.ashen.jdbctemplate.dao.IAccountDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试JdbcTemplate在持久层的使用
 */
public class JdbcTemplateDemo3 {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        IAccountDao accountDao = context.getBean("accountDao", IAccountDao.class);
        IAccountDao accountDao2 = context.getBean("accountDao2", IAccountDao.class);

        Account aaa = accountDao.findAccountByName("aaa");
        Account aaa2 = accountDao2.findAccountByName("aaa");

        System.out.println(aaa);
        System.out.println(aaa2);
    }
}
