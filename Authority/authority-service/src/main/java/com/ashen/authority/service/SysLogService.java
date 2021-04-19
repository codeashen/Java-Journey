package com.ashen.authority.service;

import com.ashen.authority.domain.SysLog;

import java.util.List;

/**
 * AOP系统日志操作业务层接口
 */
public interface SysLogService {

    /**
     * 保存AOP系统日志
     * @param log
     */
    void save(SysLog log);

    /**
     * 查询所有AOP系统日志
     * @return
     */
    List<SysLog> findAll();
}
