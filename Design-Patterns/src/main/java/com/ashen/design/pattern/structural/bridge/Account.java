package com.ashen.design.pattern.structural.bridge;

/**
 * 账号接口
 */
public interface Account {
    // 开启账号
    Account openAccount();
    // 查看账号类型
    void showAccountType();
}
