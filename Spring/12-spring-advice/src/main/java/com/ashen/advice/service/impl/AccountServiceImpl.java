package com.ashen.advice.service.impl;

import com.ashen.advice.service.IAccountService;

/**
 * 业务层接口实现类
 */
public class AccountServiceImpl implements IAccountService {
    @Override
    public void saveAccount() {
        System.out.println("模拟保存账户");
        int i = 1 / 0;
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
