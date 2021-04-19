package com.ashen.daoimpl.dao.impl;

import com.ashen.daoimpl.dao.IUserDao;
import com.ashen.daoimpl.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * 写dao实现类的方式，使用mybatis
 */
public class UserDaoImpl implements IUserDao {

    private final String NAMESPANCE = "com.ashen.daoimpl.dao.IUserDao.";

    private SqlSessionFactory factory;

    /**
     * 创建实现类时要传入SqlSessionFactory
     * @param factory SqlSessionFactory对象
     */
    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> findAll() {
        // 1.使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();

        // 2.使用SqlSession执行查询所有方法
        List<User> users = session.selectList(NAMESPANCE + "findAll");

        session.close();
        return users;
    }
}
