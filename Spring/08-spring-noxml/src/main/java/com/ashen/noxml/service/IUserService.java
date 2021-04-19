package com.ashen.noxml.service;

import com.ashen.noxml.domain.User;

import java.util.List;

/**
 * 用户的业务层接口
 */
public interface IUserService {

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAllUser();

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findUserById(Integer id);

    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id删除用户
     * @param id
     */
    void deleteUser(Integer id);
}
