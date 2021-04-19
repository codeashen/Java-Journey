package com.ashen.daoimpl.test;

import com.ashen.daoimpl.dao.IUserDao;
import com.ashen.daoimpl.dao.impl.UserDaoImpl;
import com.ashen.daoimpl.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * Mybatis入门案例
 * 使用dao实现类对象执行方法，差别在第4步
 */
public class MybatisTest {

    public static void main(String[] args) throws Exception {

        // 1.使用Resource读取配置文件，生成字节输入流（mybatis读取配置文件的方法）
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");

        // 2.创建SqlSessionFactory工厂（构建者模式）
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);

        /*// 3.使用工厂生产SqlSession对象（工厂模式）
        SqlSession session = factory.openSession();

        // 4.使用SqlSession创建dao的代理对象（代理模式）
        IUserDao userDao = session.getMapper(IUserDao.class);*/

        // 创建dao实现类对象
        IUserDao userDao = new UserDaoImpl(factory);

        // 5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }

        // 6.释放资源
        // session.close();
        in.close();
    }

}
