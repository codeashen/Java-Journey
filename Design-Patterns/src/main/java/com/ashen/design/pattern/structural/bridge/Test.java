package com.ashen.design.pattern.structural.bridge;

public class Test {
    public static void main(String[] args) {
        Bank icbcBank = new ICBCBank(new DepositAccount());
        Account account = icbcBank.openAccount();
        account.showAccountType();

        Bank abcBank = new ABCBank(new SavingAccount());
        Account account1 = abcBank.openAccount();
        account1.showAccountType();
    }
}
