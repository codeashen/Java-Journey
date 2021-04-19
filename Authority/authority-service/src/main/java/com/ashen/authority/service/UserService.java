package com.ashen.authority.service;

import com.ashen.authority.domain.Role;
import com.ashen.authority.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * 用户业务层接口
 * 继承了Spring Security 提供的 UserDetailsService接口
 */
public interface UserService extends UserDetailsService {

    /**
     * 查询所有用户
     * @return
     */
    List<UserInfo> findAll();

    /**
     * 添加用户
     * @param userInfo
     */
    void save(UserInfo userInfo);

    /**
     * 根据用户id查询用户详情
     * @param id
     * @return
     */
    UserInfo findById(String id);

    /**
     * 根据用户id查询用户未拥有的角色
     * @param userId
     * @return
     */
    List<Role> findOtherRoles(String userId);

    /**
     * 给用户添加角色
     * @param userId 用户id
     * @param roleIds 要添加的角色id数组
     */
    void addRoleToUser(String userId, String[] roleIds);
}
