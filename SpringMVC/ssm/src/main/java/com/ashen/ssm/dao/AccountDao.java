package com.ashen.ssm.dao;

import com.ashen.ssm.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户持久层接口（使用Mybatis不写实现类的方式）
 */
@Repository
public interface AccountDao {

    /**
     * 查询所有
     * @return
     */
    @Select("select * from spring_account")
    List<Account> findAll();

    /**
     * 保存账户信息
     * @param account
     */
    @Insert("insert into spring_account (name, money) values (#{name}, #{money})")
    void saveAccount(Account account);
}
