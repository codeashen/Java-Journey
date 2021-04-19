package com.ashen.ioc.anno.dao.impl;

import com.ashen.ioc.anno.dao.IUserDao;
import org.springframework.stereotype.Repository;

/**
 * 用户的持久层实现类
 */
@Repository("userDao2")
public class UserDaoImpl2 implements IUserDao {

    @Override
    public void saveUser() {
        System.out.println("保存了账户222222");
    }
}
