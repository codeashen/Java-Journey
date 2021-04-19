package com.ashen.coupling.decoupling.dao.impl;

import com.ashen.coupling.decoupling.dao.IUserDao;

/**
 * 用户持久层实现类
 */
public class UserDaoImpl implements IUserDao {

    @Override
    public void saveUser() {
        System.out.println("保存了用户");
    }
}
