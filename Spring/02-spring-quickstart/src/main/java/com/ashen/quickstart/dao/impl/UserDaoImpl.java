package com.ashen.quickstart.dao.impl;

import com.ashen.quickstart.dao.IUserDao;

/**
 * 用户持久层实现类
 */
public class UserDaoImpl implements IUserDao {

    public UserDaoImpl() {
        System.out.println("对象创建了  UserDaoImpl");
    }

    @Override
    public void saveUser() {
        System.out.println("保存了用户");
    }
}
