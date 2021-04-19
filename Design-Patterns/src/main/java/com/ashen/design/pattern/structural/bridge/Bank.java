package com.ashen.design.pattern.structural.bridge;

/**
 * 银行抽象类
 * 将Account组合到Bank里，交给子类实现Bank的行为
 *
 * 将实现层（Account）和抽象层（Bank）组合起来，形成不同的排列组合，
 * 本例中 2种账号 + 2种银行 组合可以形成 4种具体实现的账号（这里得到的是具体实现的结果数）
 * 通过Bank类可以得到不同银行的不同账号
 * 扩展上二者独立，可以扩展更多的银行和更多的账号类型，形成更多的组合关系
 */
public abstract class Bank {
    protected Account account;

    public Bank(Account account) {
        this.account = account;
    }

    abstract Account openAccount();
}
