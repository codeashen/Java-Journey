package com.ashen.anno2.dao;

import com.ashen.anno2.domain.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 注解实现多表查询和延迟加载（一对一）
 *
 * 涉及的注解详解见IUserDao接口
 */
public interface IAccountDao {

    /**
     * 查询所有账户
     * @return
     */
    @Select("select * from account")
    @Results(id = "accountMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "uid", column = "uid"),
            @Result(property = "money", column = "money"),
            @Result(property = "user", column = "uid",
                    one = @One(select = "com.ashen.anno2.dao.IUserDao.findById",
                            fetchType = FetchType.EAGER))  // 一对一，一般立即加载
    })
    List<Account> findAll();

    /**
     * 根据用户id查询改用户的账户
     * @return
     */
    @Select("select * from account where uid = #{userId}")
    List<Account> findAccountByUid();
}
