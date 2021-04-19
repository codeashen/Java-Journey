package com.ashen.design.mybatis.sqlsession.defaults;

import com.ashen.design.mybatis.cfg.Configuration;
import com.ashen.design.mybatis.sqlsession.SqlSession;
import com.ashen.design.mybatis.sqlsession.SqlSessionFactory;

/**
 * SqlSessionFactory接口的实现类
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    // Mybatis配置信息
    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg) {
        this.cfg = cfg;
    }

    /**
     * 打开一个新的SqlSession对象，数据库操作对象
     * @return 新的SqlSession会话
     */
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
