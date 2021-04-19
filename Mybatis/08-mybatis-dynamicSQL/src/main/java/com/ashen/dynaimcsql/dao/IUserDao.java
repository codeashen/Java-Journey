package com.ashen.dynaimcsql.dao;

import com.ashen.dynaimcsql.domain.QueryVo;
import com.ashen.dynaimcsql.domain.User;

import java.util.List;

public interface IUserDao {

    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();

    /**
     * 根据条件查询
     * @param user 查询条件，各属性不确定是否为空
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     * 根据vo对象中的id集合查询
     * @param vo
     * @return
     */
    List<User> findUserInIds(QueryVo vo);
}
