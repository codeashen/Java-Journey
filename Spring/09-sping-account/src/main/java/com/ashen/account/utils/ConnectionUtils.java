package com.ashen.account.utils;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 连接的工具类。用于从数据源中获取连接，并实现和线程绑定
 */
public class ConnectionUtils {

    // 存放线程私有成员变量
    private ThreadLocal<Connection> tl = new ThreadLocal<>();

    // 数据源
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取数据库连接方法
     * @return
     */
    public Connection getThreadConnection() {
        try {
            // 1.从线程上获取连接
            Connection conn = tl.get();
            // 2.判断当前线程是否有连接
            if (conn == null) {
                // 3.从数据源中获取一个连接，并存到ThreadLocal中
                conn = dataSource.getConnection();
                tl.set(conn);
            }
            // 4.返回当前线程上的连接
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 把连接和线程解绑
     */
    public void removeConnection() {
        tl.remove();
    }
}
