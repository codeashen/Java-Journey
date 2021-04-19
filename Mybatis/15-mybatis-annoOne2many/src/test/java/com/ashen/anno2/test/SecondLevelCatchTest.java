package com.ashen.anno2.test;

import com.ashen.anno2.dao.IUserDao;
import com.ashen.anno2.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 */
public class SecondLevelCatchTest {

    private InputStream in;
    private SqlSessionFactory factory;


    @Before
    public  void init()throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);

    }

    @After
    public  void destroy()throws  Exception{

        in.close();
    }

    /**
     * 测试二级缓存
     * 注解开发开启二级缓存：
     *      1. 主配置SqlMapConfig.xml中设置开启二级缓存
     *      2. dao接口IUserDao上使用@CacheNamespace(blocking = true)，开启二级缓存
     */
    @Test
    public void testFindOne(){
        SqlSession session = factory.openSession();
        IUserDao userDao = session.getMapper(IUserDao.class);
        User user = userDao.findById(41);
        System.out.println(user);

        session.close();  // 释放一级缓存

        SqlSession session1 = factory.openSession();//再次打开session
        IUserDao userDao1 = session1.getMapper(IUserDao.class);
        User user1 = userDao1.findById(41);
        System.out.println(user1);

        session1.close();

    }
}

