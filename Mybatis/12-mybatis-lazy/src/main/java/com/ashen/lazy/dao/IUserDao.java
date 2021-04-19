package com.ashen.lazy.dao;

import com.ashen.lazy.domain.User;

import java.util.List;

public interface IUserDao {

    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findById(Integer id);

}
