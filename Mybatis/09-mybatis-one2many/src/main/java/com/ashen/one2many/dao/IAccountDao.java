package com.ashen.one2many.dao;

import com.ashen.one2many.domain.Account;
import com.ashen.one2many.domain.AccountUser;

import java.util.List;

public interface IAccountDao {

    /**
     * 查询所有账户
     * @return
     */
    public List<Account> findAll();

    /**
     * 查询所有账户，并且带有所属用户的 姓名和地址
     * @return
     */
    public List<AccountUser> findAllAccount();
}
