package com.ashen.authority.domain;

import lombok.Data;

import java.util.List;

/**
 * 资源权限实体类
 */
@Data
public class Permission {
    // 主键
    private String id;
    // 权限名
    private String permissionName;
    // 资源路径
    private String url;

    // 拥有本权限的角色
    private List<Role> roles;
}
