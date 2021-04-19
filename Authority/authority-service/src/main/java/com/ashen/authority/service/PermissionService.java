package com.ashen.authority.service;

import com.ashen.authority.domain.Permission;

import java.util.List;

/**
 * 资源权限业务层接口
 */
public interface PermissionService {

    /**
     * 查询所有权限
     * @return
     */
    List<Permission> findAll();

    /**
     * 添加权限
     * @param permission
     */
    void save(Permission permission);
}
