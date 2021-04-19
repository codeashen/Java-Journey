package com.ashen.cache.test;

import com.ashen.cache.dao.IUserDao;
import com.ashen.cache.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * 测试Mybatis二级缓存
 * 二级缓存:
 *     它指的是Mybatis中SqlSessionFactory对象的缓存。
 *     由同一个SqlSessionFactory对象创建的SqlSession共享其缓存。
 * 二级缓存的使用步骤：
 *     第一步：让Mybatis框架支持二级缓存（在SqlMapConfig.xml中配置）
 *     第二步：让当前的映射文件支持二级缓存（在IUserDao.xml中配置）
 *     第三步：让当前的操作支持二级缓存（在select标签中配置）
 * 【注意】
 * 当使用了二级缓存时：
 *     控制台可以看到只有第一次查询执行了sql，但是两个User对象不是同一对象
 *     这是因为，二级缓存是数据，而不是对象，当要使用二级缓存时，取出数据封装到对象中，
 *     所以两次查询返回值不是同一对象
 */
public class SecondLevelCacheTest {

    private InputStream in;
    SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;

    /**
     * 所有测试方法之前执行的方法，此处初始化资源
     */
    @Before
    public void init() throws Exception {
        // 1.使用Resource读取配置文件，生成字节输入流（mybatis读取配置文件的方法）
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2.创建SqlSessionFactory工厂（构建者模式）
        factory = new SqlSessionFactoryBuilder().build(in);
        // 3.使用工厂生产SqlSession对象（工厂模式）
        session = factory.openSession(true);  // 传入true，设置自动提交事务
        // 4.使用SqlSession创建dao的代理对象（代理模式）
        userDao = session.getMapper(IUserDao.class);
        // 5.使用代理对象执行方法（测试方法执行）
    }

    /**
     * 所有测试方法之后执行的方法，此处提交事务+释放资源
     */
    @After
    public void destory() throws Exception {
        // 6.提交事务
        // session.commit();   // 设置了自动提交事务，不需要了
        // 7.释放资源
        session.close();
        in.close();
    }

    /**
     * 测试Mybatis二级缓存
     */
    @Test
    public void testFirstLevelCache() {

        SqlSession session1 = factory.openSession();
        IUserDao userDao1 = session1.getMapper(IUserDao.class);
        System.out.println("========== 第一次查询 ==========");
        User user1 = userDao1.findById(41);
        System.out.println(user1);
        session1.close();  // 一级缓存消失

        SqlSession session2 = factory.openSession();
        IUserDao userDao2 = session2.getMapper(IUserDao.class);
        System.out.println("========== 第二次查询 ==========");
        User user2 = userDao2.findById(41);
        System.out.println(user2);
        session2.close();

        /*
        控制台可以看到只有第一次查询执行了sql，但是两个User对象不是同一对象
        这是因为，二级缓存是数据，而不是对象，当要使用二级缓存时，取出数据封装到对象中，
        所以两次查询返回值不是同一对象
         */

        System.out.println(user1 == user2);  // false
    }

}
