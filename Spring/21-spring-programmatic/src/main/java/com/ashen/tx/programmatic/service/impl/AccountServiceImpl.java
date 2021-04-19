package com.ashen.tx.programmatic.service.impl;

import com.ashen.tx.programmatic.dao.IAccountDao;
import com.ashen.tx.programmatic.domain.Account;
import com.ashen.tx.programmatic.service.IAccountService;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 展示了如何使用TransactionTemplate实现编程式事务控制。
 * 这种方式每个业务方法上都要加上重复的代码片段，不灵活，所以不如声明式好。
 */
public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;
    // 事务模板对象，使用该对象实现编程式事务控制
    private TransactionTemplate transactionTemplate;

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account findAccountById(Integer accountId) {
        return transactionTemplate.execute(new TransactionCallback<Account>() {
            @Override
            public Account doInTransaction(TransactionStatus transactionStatus) {
                return accountDao.findAccountById(accountId);
            }
        });
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
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
                return null;
            }
        });
    }
}
