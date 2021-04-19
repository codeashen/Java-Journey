package com.ashen.many2many.dao;

import com.ashen.many2many.domain.Role;

import java.util.List;

public interface IRoleDao {

    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAll();
}
