package com.ashen.authority.domain;

import lombok.Data;

/**
 * 会员实体类
 */
@Data
public class Member {
    // 主键
    private String id;
    // 姓名
    private String name;
    // 昵称
    private String nickname;
    // 电话号码
    private String phoneNum;
    // 邮箱
    private String email;
}
