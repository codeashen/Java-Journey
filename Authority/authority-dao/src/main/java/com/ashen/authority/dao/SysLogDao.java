package com.ashen.authority.dao;

import com.ashen.authority.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AOP系统日志持久层接口
 */
@Repository
public interface SysLogDao {

    /**
     * 保存AOP系统日志
     * @param log
     */
    @Insert("insert into authority_syslog(visitTime, username, ip, url, executionTime, method) " +
            "values(#{visitTime}, #{username}, #{ip}, #{url}, #{executionTime}, #{method})")
    void save(SysLog log);

    /**
     * 查询所有AOP系统日志
     * @return
     */
    @Select("select * from authority_syslog")
    @Results({
            @Result(id=true, column="id", property="id"),
            @Result(column="visitTime", property="visitTime"),
            @Result(column="ip", property="ip"),
            @Result(column="url", property="url"),
            @Result(column="executionTime", property="executionTime"),
            @Result(column="method", property="method"),
            @Result(column="username", property="username")
    })
    List<SysLog> findAll();
}
