package com.ashen.design.mybatis.utils;

import com.ashen.design.mybatis.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 获取数据库连接的工具类
 */
public class DataSourceUtil {

    /**
     * 获取数据库连接
     * @param cfg Mybatis配置对象
     * @return
     */
    public static Connection getConnection(Configuration cfg) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(cfg.getUrl(), cfg.getUsername(), cfg.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
