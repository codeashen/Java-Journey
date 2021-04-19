package com.ashen.authority.service;

import com.ashen.authority.domain.Permission;
import com.ashen.authority.domain.Role;

import java.util.List;

/**
 * 角色业务层接口
 */
public interface RoleService {

    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAll();

    /**
     * 添加角色
     * @param role
     */
    void save(Role role);

    /**
     * 根据id查询角色信息
     * @param roleId
     * @return
     */
    Role findById(String roleId);

    /**
     * 根据角色id查询该权限未关联的权限
     * @param roleId
     * @return
     */
    List<Permission> findOtherPermission(String roleId);

    /**
     * 给角色添加权限
     * @param roleId 角色id
     * @param permissionIds 要添加的权限id数组
     */
    void addPermissionToRole(String roleId, String[] permissionIds);
}
