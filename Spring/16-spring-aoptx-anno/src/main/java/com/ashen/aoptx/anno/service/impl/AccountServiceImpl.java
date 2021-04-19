package com.ashen.aoptx.anno.service.impl;

import com.ashen.aoptx.anno.domain.Account;
import com.ashen.aoptx.anno.dao.IAccountDao;
import com.ashen.aoptx.anno.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 账户业务层实现类
 *
 * 【务控制应该都是在业务层的】
 * 使用自定义的事务管理器，实现了手动控制事务，让转账操作正确运行，
 * 但是造成了代码臃肿，配置繁琐，所以引出动态代理，进一步引出AOP
 */
@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    @Override
    public Account findAccountById(Integer id) {
        return accountDao.findAccountById(id);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void transfer(Integer sourceId, Integer targetId, Double money) {
        System.out.println("开始转账...");
        // 1.根据id查出转出账户
        Account source = accountDao.findAccountById(sourceId);
        // 2.根据id查出转入账户
        Account target = accountDao.findAccountById(targetId);
        // 3.转出账户扣钱
        if (source.getMoney() < money) {
            throw new RuntimeException("账户余额不足，无法转账");
        } else {
            source.setMoney(source.getMoney() - money);
        }
        // 4.转入账户加钱
        target.setMoney(target.getMoney() + money);
        // 5.更新转出账户
        accountDao.updateAccount(source);
        int i = 1 / 0;
        // 6.更新转入账户
        accountDao.updateAccount(target);
    }
}
