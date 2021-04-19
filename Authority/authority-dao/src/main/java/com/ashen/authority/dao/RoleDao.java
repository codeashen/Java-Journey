package com.ashen.authority.dao;

import com.ashen.authority.domain.Permission;
import com.ashen.authority.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色持久层接口
 */
@Repository
public interface RoleDao {

    /**
     * 根据用户id查询用户拥有的角色
     * @param userId
     * @return
     */
    @Select("select * from authority_role where id in " +
            "(select roleId from authority_user_role where userId = #{userId})")
    @Results(value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "roleName", property = "roleName"),
            @Result(column = "roleDesc", property = "roleDesc"),
            @Result(column = "id", property = "permissions", javaType = List.class,
                    many = @Many(select = "com.ashen.authority.dao.PermissionDao.findPermissionsByRoleId"))
    })
    List<Role> findRolesByUserId(String userId);

    /**
     * 查询所有角色
     * @return
     */
    @Select("select * from authority_role")
    List<Role> findAll();

    /**
     * 添加角色
     * @param role
     */
    @Insert("insert into authority_role(roleName, roleDesc) values(#{roleName}, #{roleDesc})")
    void save(Role role);

    /**
     * 根据id查询角色信息
     * @param roleId
     * @return
     */
    @Select("select * from authority_role where id = #{roleId}")
    Role findById(String roleId);

    /**
     * 根据角色id查询该权限未关联的权限
     * @param roleId
     * @return
     */
    @Select("select * from authority_permission where id not in " +
            "(select permissionId from authority_role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermission(String roleId);

    /**
     * 给角色添加权限
     * @param roleId 角色id
     * @param permissionId 要添加的权限id
     * @return
     */
    @Insert("insert into authority_role_permission(roleId, permissionId) values(#{roleId}, #{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
