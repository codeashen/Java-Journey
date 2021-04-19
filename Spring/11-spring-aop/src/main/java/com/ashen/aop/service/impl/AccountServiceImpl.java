package com.ashen.aop.service.impl;

import com.ashen.aop.service.IAccountService;

/**
 * 业务层接口实现类
 */
public class AccountServiceImpl implements IAccountService {
    @Override
    public void saveAccount() {
        System.out.println("模拟保存账户");
    }

    @Override
    public void updateAccount(int i) {
        System.out.println("模拟更新账户  " + i);
    }

    @Override
    public int deleteAccount() {
        System.out.println("模拟删除账户");
        return 5;
    }
}
