package com.ashen.coupling.decoupling.service.impl;

import com.ashen.coupling.decoupling.dao.IUserDao;
import com.ashen.coupling.decoupling.dao.impl.UserDaoImpl;
import com.ashen.coupling.decoupling.service.IUserService;

/**
 * 用户业务层接口实现类
 */
public class UserServiceImpl implements IUserService {

    private IUserDao userDao = new UserDaoImpl();

    @Override
    public void saveUser() {
        userDao.saveUser();
    }
}
