package com.ashen.design.mybatis.sqlsession;

import com.ashen.design.mybatis.cfg.Configuration;
import com.ashen.design.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import com.ashen.design.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * SqlSessionFactory构建者类
 * 用于创建SqlSessionFactory工厂类
 */
public class SqlSessionFactoryBuilder {

    /**
     * 根据配置文件的字节输入流，构建SqlSessionFactory工厂
     * @param config 配置文件的字节输入流
     * @return SqlSessionFactory工厂
     */
    public SqlSessionFactory build(InputStream config) {
        // 使用工具类解析XML，得到配置对象
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        // 创建SqlSessionFactory默认实现类
        return new DefaultSqlSessionFactory(cfg);
    }
}
