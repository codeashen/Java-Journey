package com.ashen.cache.dao;

import com.ashen.cache.domain.User;

import java.util.List;

public interface IUserDao {

    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);

}
