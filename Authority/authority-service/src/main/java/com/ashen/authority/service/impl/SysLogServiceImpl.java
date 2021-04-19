package com.ashen.authority.service.impl;

import com.ashen.authority.dao.SysLogDao;
import com.ashen.authority.domain.SysLog;
import com.ashen.authority.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * AOP系统日志业务层接口实现类
 */
@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public void save(SysLog log) {
        sysLogDao.save(log);
    }

    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }
}
