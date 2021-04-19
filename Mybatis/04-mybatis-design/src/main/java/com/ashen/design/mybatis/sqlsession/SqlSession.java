package com.ashen.design.mybatis.sqlsession;

/**
 * 数据库操作对象
 * 自定义Mybatis中和数据库交换的核心接口
 * 它可以创建dao接口的代理对象，执行数据库操作
 */
public interface SqlSession {

    /**
     * 获取dao接口的代理对象
     * @param daoInterfaceClass dao接口的字节码对象
     * @param <T> dao泛型
     * @return
     */
    <T> T getMapper(Class<T> daoInterfaceClass);

    /**
     * 释放资源，此处就是关数据库连接
     */
    void close();
}
