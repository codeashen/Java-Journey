package com.ashen.db.xml.service.impl;

import com.ashen.db.xml.dao.IUserDao;
import com.ashen.db.xml.domain.User;
import com.ashen.db.xml.service.IUserService;

import java.util.List;

/**
 * 用户的业务层实现类
 */
public class UserServiceImpl implements IUserService {

    private IUserDao userDao;

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    public User findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userDao.deleteUser(id);
    }
}
