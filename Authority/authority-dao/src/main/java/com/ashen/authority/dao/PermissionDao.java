package com.ashen.authority.dao;

import com.ashen.authority.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 资源权限持久层接口
 */
@Repository
public interface PermissionDao {

    /**
     * 根据角色id查询该角色拥有的所有权限
     * @param roleId
     * @return
     */
    @Select("select * from authority_permission where id in " +
            "(select permissionId from authority_role_permission where roleId = #{id})")
    List<Permission> findPermissionsByRoleId(String roleId);

    /**
     * 查询所有角色
     * @return
     */
    @Select("select * from authority_permission")
    List<Permission> findAll();

    /**
     * 添加权限
     * @param permission
     */
    @Insert("insert into authority_permission(permissionName, url) values(#{permissionName}, #{url})")
    void save(Permission permission);
}
