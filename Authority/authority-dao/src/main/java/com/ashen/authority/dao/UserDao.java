package com.ashen.authority.dao;

import com.ashen.authority.domain.Role;
import com.ashen.authority.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户持久层接口
 */
@Repository
public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Select("select * from authority_user where username = #{username}")
    @Results(id = "userMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = List.class,
                    many = @Many(select = "com.ashen.authority.dao.RoleDao.findRolesByUserId"))
    })
    UserInfo findByUsername(String username);

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from authority_user")
    List<UserInfo> findAll();

    /**
     * 添加用户
     * @param userInfo
     */
    @Insert("insert into authority_user(username,password,email,phoneNum,status) " +
            "values(#{username},#{password},#{email},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Select("select * from authority_user where id = #{id}")
    @ResultMap("userMap")  // 应用上面定义好的
    UserInfo findById(String id);

    /**
     * 根据用户id查询用户未拥有的角色
     * @param userId
     * @return
     */
    @Select("select * from authority_role where id not in " +
            "(select roleId from authority_user_role where userId = #{userId})")
    List<Role> findOtherRoles(String userId);

    /**
     * 给用户添加角色
     * @param userId 用户id
     * @param roleId 要添加的角色id
     */
    @Insert("insert into authority_user_role(userId, roleId) values(#{userId}, #{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
