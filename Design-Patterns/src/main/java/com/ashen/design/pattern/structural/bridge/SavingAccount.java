package com.ashen.design.pattern.structural.bridge;

/**
 * 活期账号类
 */
public class SavingAccount implements Account {
    @Override
    public Account openAccount() {
        return new SavingAccount();
    }

    @Override
    public void showAccountType() {
        System.out.println("这是一个活期账号");
    }
}
