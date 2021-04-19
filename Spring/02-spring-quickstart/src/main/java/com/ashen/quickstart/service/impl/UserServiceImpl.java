package com.ashen.quickstart.service.impl;

import com.ashen.quickstart.dao.IUserDao;
import com.ashen.quickstart.service.IUserService;

/**
 * 用户业务层接口实现类
 */
public class UserServiceImpl implements IUserService {

    private IUserDao userDao;

    public UserServiceImpl() {
        System.out.println("对象创建了  UserServiceImpl");
    }

    @Override
    public void saveUser() {
        userDao.saveUser();
    }
}
