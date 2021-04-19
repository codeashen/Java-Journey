package com.ashen.account.service.impl;

import com.ashen.account.dao.IAccountDao;
import com.ashen.account.domain.Account;
import com.ashen.account.service.IAccountService;
import com.ashen.account.utils.TransactionManager;

/**
 * 账户业务层实现类
 *
 * 【务控制应该都是在业务层的】
 * 使用自定义的事务管理器，实现了手动控制事务，让转账操作正确运行，
 * 但是造成了代码臃肿，配置繁琐，所以引出动态代理，进一步引出AOP
 */
public class AccountServiceImpl_OLD implements IAccountService {

    private IAccountDao accountDao;

    // 注入事务管理工具类，使业务受事务控制
    private TransactionManager txManager;

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account findAccountById(Integer id) {
        try {
            // 1. 开启事务
            txManager.beginTx();
            // 2.执行操作
            Account account = accountDao.findAccountById(id);
            // 3.提交事务
            txManager.commit();
            // 4.返回结果
            return account;
        } catch (Exception e) {
            // 5.回滚事务
            txManager.rollback();
        } finally {
            // 6.释放连接
            txManager.release();
        }
        return null;
    }

    @Override
    public void updateAccount(Account account) {
        try {
            // 1. 开启事务
            txManager.beginTx();
            // 2.执行操作
            accountDao.updateAccount(account);
            // 3.提交事务
            txManager.commit();
        } catch (Exception e) {
            // 4.回滚事务
            txManager.rollback();
        } finally {
            // 5.释放连接
            txManager.release();
        }
    }

    @Override
    public void transfer(Integer sourceId, Integer targetId, Double money) {
        try {
            // 1. 开启事务
            txManager.beginTx();

            // 2.执行操作
            // 2.1根据id查出转出账户
            Account source = accountDao.findAccountById(sourceId);
            // 2.2根据id查出转入账户
            Account target = accountDao.findAccountById(targetId);
            // 2.3转出账户扣钱
            if (source.getMoney() < money) {
                throw new RuntimeException("账户余额不足，无法转账");
            } else {
                source.setMoney(source.getMoney() - money);
            }
            // 2.4转入账户加钱
            target.setMoney(target.getMoney() + money);
            // 2.5更新转出账户
            accountDao.updateAccount(source);
            int i = 1 / 0;
            // 2.6更新转入账户
            accountDao.updateAccount(target);
            
            // 3.提交事务
            txManager.commit();
        } catch (Exception e) {
            // 4.回滚事务
            txManager.rollback();
        } finally {
            // 5.释放连接
            txManager.release();
        }
    }
}
