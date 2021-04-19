package com.ashen.daoimpl2.dao;

import com.ashen.daoimpl2.domain.User;

import java.util.List;

public interface IUserDao {

    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();

    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新方法
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除方法
     * @param id
     */
    void deleteUserById(int id);

    /**
     * 查找一个
     * @param id
     * @return
     */
    User findById(int id);

    /**
     * 模糊查询
     * @param keyword
     * @return
     */
    List<User> findByName(String keyword);

    /**
     * 查询总记录条数
     * @return
     */
    int findTotal();

}
