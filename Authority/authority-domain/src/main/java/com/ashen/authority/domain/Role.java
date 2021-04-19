package com.ashen.authority.domain;

import lombok.Data;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * 角色实体类
 */
@Data
public class Role {
    // 主键
    private String id;
    // 角色名
    private String roleName;
    // 角色描述
    private String roleDesc;

    // 本角色拥有的权限
    private List<Permission> permissions;
    // 拥有本角色的用户
    private List<User> users;
}
