package com.ashen.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC工具类，使用Druid连接池
 */
public class JDBCUtils {

    private static DataSource ds;

    static {
        try {
            // 1.加载配置文件
            Properties ps = new Properties();
            InputStream ins = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            ps.load(ins);

            // 2.初始化连接池
            ds = DruidDataSourceFactory.createDataSource(ps);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取连接池
     */
    public static DataSource getDataSource() {
        return ds;
    }

    /**
     * 获取Connection连接对象
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
