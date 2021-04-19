package com.ashen.tx.withoutxml.service.impl;

import com.ashen.tx.withoutxml.domain.Account;
import com.ashen.tx.withoutxml.service.IAccountService;
import com.ashen.tx.withoutxml.dao.IAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 账户的业务层实现类
 * Transactional注解用于开启事务，类和方法上可用
 * 其中的属性用于配置事务的属性，类上的配置对所有方法有效
 * 方法上的注解配置会覆盖类上的注解配置
 */
@Service("accountService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true) //只读事务
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    @Override
    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    /**
     * Transactional注解覆盖了类上的配置，为读写型事务
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void transfer(String sourceName, String targetName, Float money) {
        System.out.println("transfer....");
        //2.1根据名称查询转出账户
        Account source = accountDao.findAccountByName(sourceName);
        //2.2根据名称查询转入账户
        Account target = accountDao.findAccountByName(targetName);
        //2.3转出账户减钱
        source.setMoney(source.getMoney()-money);
        //2.4转入账户加钱
        target.setMoney(target.getMoney()+money);
        //2.5更新转出账户
        accountDao.updateAccount(source);

        int i=1/0;

        //2.6更新转入账户
        accountDao.updateAccount(target);
    }
}
