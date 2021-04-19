package com.ashen.authority.domain;

import lombok.Data;

import java.util.List;

/**
 * 用户实体类，对应user表
 */
@Data
public class UserInfo {
    // 主键
    private String id;
    // 用户名
    private String username;
    // 邮箱
    private String email;
    // 密码
    private String password;
    // 电话号码
    private String phoneNum;
    // 用户状态（0 未启用 1启用）
    private Integer status;

    // 本用户拥有的角色
    private List<Role> roles;

    // 用户状态字符串
    private String statusStr;

    /**
     * 设置用户状态，及其字符串形式
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
        if (status != null) {
            if (status == 0) {
                statusStr = "未启用";
            }
            if (status == 1) {
                statusStr = "启用";
            }
        }
    }
}
