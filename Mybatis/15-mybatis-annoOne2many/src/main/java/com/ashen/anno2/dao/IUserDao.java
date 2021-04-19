package com.ashen.anno2.dao;

import com.ashen.anno2.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 注解实现多表查询和延迟加载（一对多）
 *
 * 多表查询涉及的注解：
 *
 * 1. 注解    @Results      就是resultMap，在方法上使用
 *    属性    id            resultMap的id，供其他方法使用，不需要在配置一次@Result
 *            value         @Result注解数组，就是字段映射
 *
 * 2. 注解    @Result       就是resultMap中的字段映射
 *    属性    id            默认false，true表示是主键，否则不是
 *            column        数据表字段名
 *            property      实体类属性名
 *            one           @One注解，用在一对一的映射关系
 *            many          @Many注解，用在一对多的映射关系
 *
 * 3. 注解    @One/@Many    多表查询时使用的，此时@Result的column属性表示另一个查询的参数
 *    属性    select        查询语句的映射
 *            fetchType     加载策略，枚举值。LAZY延迟，EAGER立即，DEFAULT默认
 *
 * 4. 注解    @ResultMap    用在查询方法上，表示使用@Results注解配置的resultMap
 *    属性    value         对应@Results注解的id属性
 */
@CacheNamespace(blocking = true)  // 开启二级缓存 blocking = true
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    @Results(id = "userMap", value = {
            @Result(column = "id", property = "userId", id = true),
            @Result(column = "username", property = "userName"),
            @Result(column = "birthday", property = "birthday"),
            @Result(column = "sex", property = "gender"),
            @Result(column = "address", property = "address"),
            @Result(property = "accounts", column = "id", // 这里的column表示查询Account的参数
                    many = @Many(select = "com.ashen.anno2.dao.IAccountDao.findAccountByUid",
                            fetchType = FetchType.LAZY))
    })
    List<User> findAll();

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    @Select("select * from user where id = #{userId}")
    @ResultMap("userMap")
    User findById(Integer userId);
}
