package com.ashen.design.test;

import com.ashen.design.dao.IUserDao;
import com.ashen.design.domain.User;
import com.ashen.design.mybatis.io.Resources;
import com.ashen.design.mybatis.sqlsession.SqlSession;
import com.ashen.design.mybatis.sqlsession.SqlSessionFactory;
import com.ashen.design.mybatis.sqlsession.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * 自定义Mybatis
 * XML + 注解 方式
 */
public class MybatisTest {

    public static void main(String[] args) throws Exception {

        // 1.使用Resource读取配置文件，生成字节输入流（mybatis读取配置文件的方法）
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");

        // 2.创建SqlSessionFactory工厂（构建者模式）
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);

        // 3.使用工厂生产SqlSession对象（工厂模式）
        SqlSession session = factory.openSession();

        // 4.使用SqlSession创建dao的代理对象（代理模式）
        IUserDao userDao = session.getMapper(IUserDao.class);

        // 5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }

        // 6.释放资源
        session.close();
        in.close();
    }

}
