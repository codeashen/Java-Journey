package com.ashen.authority.service.impl;

import com.ashen.authority.dao.UserDao;
import com.ashen.authority.domain.Role;
import com.ashen.authority.domain.UserInfo;
import com.ashen.authority.service.UserService;
import com.ashen.authority.utils.PasswordEncodingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户业务层接口实现类
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    /**
     * Spring Security提供的密码加密工具类，在spring-security.xml有配置这个bean
     * 可以配置bean注入使用，也可以像在PasswordEncodingUtils工具类一样直接使用
     * @see PasswordEncodingUtils#encodePassword(String)
     */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Spring Security 提供的 UserDetailsService接口中的方法
     * @param username 用户名
     * @return 返回值是UserDetails接口，它有一个User实现类
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findByUsername(username);
        /*
        这个User类是UserDetails接口的实现类，使用查到的userInfo初始化该对象
        参数一：String username，用户名
        参数二：String password，密码，加前缀 {noop}表示不加密的密码
        参数三：Collection<? extends GrantedAuthority> authorities，权限集合，泛型是权限接口

        User user = new User(userInfo.getUsername(), "{noop}" + userInfo.getPassword(), this.getAuthority(userInfo.getRoles()));
        */
        User user = new User(userInfo.getUsername(),
                userInfo.getPassword(),
                userInfo.getStatus() == 1 ? true : false,  // 账户是否可用
                true,  // 账户未过期
                true,  // 凭证未过期
                true,  // 账户未锁定
                getAuthority(userInfo.getRoles()));
        return user;
    }

    /**
     * 获取权限集合
     * @param roles 用户的角色List
     * @return 集合。泛型为接口，这里使用其实现类，集合用List
     */
    private Collection<? extends GrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        // 遍历用户的角色，封装到目标对象中
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) {
        // 密码加密处理
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findOtherRoles(String userId) {
        return userDao.findOtherRoles(userId);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        for (String roleId : roleIds) {
            userDao.addRoleToUser(userId, roleId);
        }
    }
}
