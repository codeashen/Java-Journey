package com.ashen.db.anno.dao.impl;

import com.ashen.db.anno.domain.User;
import com.ashen.db.anno.dao.IUserDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户的持久层实现类
 */
@Repository("userDao")
public class UserDaoImpl implements IUserDao {

    // dbutils的执行sql对象，注解注入，无需setter方法
    @Autowired
    private QueryRunner runner;

    @Override
    public List<User> findAllUser() {
        try {
            return runner.query("select * from user", new BeanListHandler<User>(User.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findUserById(Integer id) {
        try {
            return runner.query("select * from user where id = ?",
                    new BeanHandler<User>(User.class), id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(User user) {
        try {
            runner.update("insert into user(username, birthday, sex) values(?, ?, ?)",
                    user.getUsername(), user.getBirthday(), user.getSex());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            runner.update("update user set username = ? where id = ?",
                    user.getUsername(), user.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(Integer id) {
        try {
            runner.update("delete from user where id = ?", id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
