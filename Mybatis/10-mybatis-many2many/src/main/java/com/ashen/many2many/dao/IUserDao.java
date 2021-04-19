package com.ashen.many2many.dao;

import com.ashen.many2many.domain.User;

import java.util.List;

public interface IUserDao {

    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();

}
