package com.ashen.daoimpl2.dao.impl;

import com.ashen.daoimpl2.dao.IUserDao;
import com.ashen.daoimpl2.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements IUserDao {

    private final String NAMESPACE = "com.ashen.daoimpl2.dao.IUserDao.";

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> findAll() {
        // 1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        // 2.调用SqlSession中的方法执行数据库操作
        List<User> users = session.selectList(NAMESPACE + "findAll");
        // 3.释放资源
        session.close();
        return users;
    }

    @Override
    public void saveUser(User user) {
        SqlSession session = factory.openSession();
        session.insert(NAMESPACE + "saveUser", user);
        session.close();
    }

    @Override
    public void updateUser(User user) {
        SqlSession session = factory.openSession();
        session.update(NAMESPACE + "updateUser", user);
        session.close();
    }

    @Override
    public void deleteUserById(int id) {
        SqlSession session = factory.openSession();
        session.delete(NAMESPACE + "deleteUserById", id);
        session.close();
    }

    @Override
    public User findById(int id) {
        SqlSession session = factory.openSession();
        User user = session.selectOne(NAMESPACE + "findById", id);
        session.close();
        return user;
    }

    @Override
    public List<User> findByName(String keyword) {
        SqlSession session = factory.openSession();
        List<User> users = session.selectList(NAMESPACE + "findByName", keyword);
        session.close();
        return users;
    }

    @Override
    public int findTotal() {
        SqlSession session = factory.openSession();
        Integer count = session.selectOne(NAMESPACE + "findTotal");
        session.close();
        return count;
    }
}
