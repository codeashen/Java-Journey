package com.ashen.design.mybatis.sqlsession.defaults;

import com.ashen.design.mybatis.cfg.Configuration;
import com.ashen.design.mybatis.sqlsession.SqlSession;
import com.ashen.design.mybatis.sqlsession.proxy.MapperProxy;
import com.ashen.design.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * SqlSession接口实现类
 */
public class DefaultSqlSession implements SqlSession {

    // Mybatis配置，构造方法初始化
    private Configuration cfg;
    // 数据库连接，构造方法初始化
    private Connection connection;

    public DefaultSqlSession(Configuration cfg) {
        this.cfg = cfg;
        this.connection = DataSourceUtil.getConnection(cfg);
    }

    /**
     * 获取dao接口的代理对象
     * @param daoInterfaceClass dao接口的字节码对象
     * @param <T> dao泛型
     * @return
     */
    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        /*动态代理：
             涉及的类：Proxy
             使用的方法：newProxyInstance
             方法的参数：
               ClassLoader：和被代理对象使用相同的类加载器,通常都是固定的
               Class[]：代理对象和被代理对象要求有相同的行为。（具有相同的方法）
               InvocationHandler：如何代理。需要我们自己提供的增强部分的代码*/
        return  (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass},
                new MapperProxy(cfg.getMappers(), connection));
    }

    /**
     * 释放资源，此处就是关数据库连接
     */
    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
