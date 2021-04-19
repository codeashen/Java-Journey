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
 * 测试Mybatis一级缓存
 * 一级缓存是SqlSession范围的缓存，一级缓存是默认开启的，无需配置
 * 当调用SqlSession的修改，添加，删除，commit()，close()等方法时，就会清空一级缓存。
 */
public class FirstLevelCacheTest {

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
     * 测试Mybatis一级缓存
     */
    @Test
    public void testFirstLevelCache() {
        System.out.println("========== 第一次查询 ==========");
        User user1 = userDao.findById(41);
        System.out.println(user1);

        System.out.println("========== 第二次查询 ==========");
        User user2 = userDao.findById(41);
        System.out.println(user2);
        /* 控制台可以看到只有第一次查询执行了sql */

        System.out.println(user1 == user2);  // true
    }

    /**
     * 测试Mybatis一级缓存在SqlSession中
     */
    @Test
    public void testFirstLevelCache2() {
        System.out.println("========== 第一次查询 ==========");
        User user1 = userDao.findById(41);
        System.out.println(user1);

        // 关闭session，重新获取
        session.close();
        session = factory.openSession(true);
        userDao = session.getMapper(IUserDao.class);

        System.out.println("========== 第二次查询 ==========");
        User user2 = userDao.findById(41);
        System.out.println(user2);
        /* 两次都执行了sql查询 */

        System.out.println(user1 == user2);  // false
    }

    /**
     * 测试SqlSession清除一级缓存
     */
    @Test
    public void testFirstLevelCache3() {
        System.out.println("========== 第一次查询 ==========");
        User user1 = userDao.findById(41);
        System.out.println(user1);

        // 清除一级缓存
        session.clearCache();

        System.out.println("========== 第二次查询 ==========");
        User user2 = userDao.findById(41);
        System.out.println(user2);
        /* 两次都执行了sql查询 */

        System.out.println(user1 == user2);  // false
    }

    /**
     * 测试SqlSession清除一级缓存
     */
    @Test
    public void testFirstLevelCache4() {
        System.out.println("========== 第一次查询 ==========");
        User user1 = userDao.findById(41);
        System.out.println(user1);

        // 更新用户信息
        user1.setAddress("安徽合肥");
        userDao.updateUser(user1);

        System.out.println("========== 第二次查询 ==========");
        User user2 = userDao.findById(41);
        System.out.println(user2);
        /* 两次都执行了sql查询 */

        System.out.println(user1 == user2);  // false
    }

}
