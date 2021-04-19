package com.ashen.crud.dao;

import com.ashen.crud.domain.QueryVo;
import com.ashen.crud.domain.User;

import java.util.List;

public interface IUserDao {

    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();

    /**
     * 【保存方法】
     * 保存用户，并获取到数据库自增长的id
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
     * @param userId
     */
    void deleteUserById(int userId);

    /**
     * 根据id查询
     * @param userId
     * @return
     */
    User findById(int userId);

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

    /**
     * VO对象作为查询条件
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);
}
