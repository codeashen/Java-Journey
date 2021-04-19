package com.ashen.dynamicsql.test;

import com.ashen.dynaimcsql.dao.IUserDao;
import com.ashen.dynaimcsql.domain.QueryVo;
import com.ashen.dynaimcsql.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 测试Mybatis的动态SQL
 */
public class MybatisTest {

    private InputStream in;
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
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

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
     * 测试查询所有，测试include标签引用sql片段
     */
    @Test
    public void testFindAll() {
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试根据条件查询，if标签和where标签
     */
    @Test
    public void testFindByCondition() {
        User u = new User();
        u.setUsername("老王");
        u.setSex("男");

        List<User> users = userDao.findUserByCondition(u);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试foreach标签
     */
    @Test
    public void testFindInIds() {
        QueryVo vo = new QueryVo();
        List<Integer> ids = Arrays.asList(41, 42, 43);
        vo.setIds(ids);

        List<User> users = userDao.findUserInIds(vo);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
