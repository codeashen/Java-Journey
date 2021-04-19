package com.ashen.tx.programmatic.service;

import com.ashen.tx.programmatic.domain.Account;

/**
 * 账户的业务层接口
 */
public interface IAccountService {

    /**
     * 根据id查询账户
     * @param accountId
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 转账操作
     * @param sourceName  转出账户名
     * @param targetName  转入账户名
     * @param money       转账金额
     */
    void transfer(String sourceName, String targetName, Float money);
}
