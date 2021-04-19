package com.ashen.design.pattern.structural.bridge;

/**
 * 中国工商银行
 */
public class ICBCBank extends Bank {

    public ICBCBank(Account account) {
        super(account);
    }

    @Override
    Account openAccount() {
        System.out.println("打开中国农业银行账号");
        return account;
    }
}
