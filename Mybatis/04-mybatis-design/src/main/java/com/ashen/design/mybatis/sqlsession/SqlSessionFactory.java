package com.ashen.design.mybatis.sqlsession;

/**
 * SqlSessionFactory工厂接口
 */
public interface SqlSessionFactory {

    /**
     * 打开一个新的SqlSession对象，数据库操作对象
     * @return 新的SqlSession会话
     */
    SqlSession openSession();
}
