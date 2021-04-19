package com.ashen.jndi.dao;

import com.ashen.jndi.domain.User;

import java.util.List;

public interface IUserDao {

    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();

}
