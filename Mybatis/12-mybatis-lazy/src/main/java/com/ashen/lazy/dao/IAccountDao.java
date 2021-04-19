package com.ashen.lazy.dao;

import com.ashen.lazy.domain.Account;

import java.util.List;

public interface IAccountDao {

    /**
     * 查询所有账户
     * @return
     */
    List<Account> findAll();

    /**
     * 根据uid查询用户的所有账户
     * @param uid 用户uid
     * @return
     */
    List<Account> findAccountByUid(Integer uid);

}
