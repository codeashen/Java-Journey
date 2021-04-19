package com.ashen.account.dao.impl;

import com.ashen.account.dao.IAccountDao;
import com.ashen.account.utils.ConnectionUtils;
import com.ashen.account.domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * 账户持久层接口实现类
 *
 * 原来在bean.xml中配置QueryRunner时，使用构造方式注入了数据源，
 * 这样的话QueryRunner执行数据库操作时都会从该数据源中取一个连接，
 * 但是此时显然不希望这样，应该使用线程中绑定的连接，以手动控制事务，
 * 所以bean.xml中配置QueryRunner中不注入Connection，
 * 但是这样怎么操作数据库呢？
 *
 * 所以在此Dao实现类中注入ConnectionUtils，使用其中的连接，
 * QueryRunner执行数据库操作时，将连接作为参数传入
 */
public class AccountDaoImpl implements IAccountDao {

    // dbUtils执行sql核心对象
    private QueryRunner runner;

    // 注入ConnectionUtils，为QueryRunner的数据库操作提供连接
    private ConnectionUtils connectionUtils;

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    @Override
    public Account findAccountById(Integer id) {
        try {
            // 使用这种方法重载，执行操作时将连接作为参数传入
            List<Account> accounts = runner.query(connectionUtils.getThreadConnection(),
                    "select * from account where id = ?",
                    new BeanListHandler<Account>(Account.class), id);
            if (accounts == null || accounts.size() == 0) {
                return null;
            }
            if (accounts.size() > 1) {
                throw new RuntimeException("结果集不唯一，数据有问题！");
            }
            return accounts.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            // 使用这种方法重载，执行操作时将连接作为参数传入
            runner.update(connectionUtils.getThreadConnection(),
                    "update account set money = ? where id =?",
                    account.getMoney(), account.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
