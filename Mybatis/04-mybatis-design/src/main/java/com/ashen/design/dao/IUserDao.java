package com.ashen.design.dao;

import com.ashen.design.domain.User;
import com.ashen.design.mybatis.annotations.Select;

import java.util.List;

public interface IUserDao {

    /**
     * 查询所有操作
     * @return
     */
    @Select("select * from user")
    List<User> findAll();
}
