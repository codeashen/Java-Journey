package com.ashen.account.service;

import com.ashen.account.domain.Account;

/**
 * 账户业务层接口
 */
public interface IAccountService {

    /**
     * 根据账户id查询账户
     * @param id
     * @return
     */
    Account findAccountById(Integer id);

    /**
     * 更新账户
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 【转账操作】，演示事务
     * @param sourceId 转出账户id
     * @param targetId 转入账户id
     * @param money 转账金额
     */
    void transfer(Integer sourceId, Integer targetId, Double money);
}
