package com.ashen.lazy.test;

import com.ashen.lazy.dao.IAccountDao;
import com.ashen.lazy.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * 测试Mybatis一对一的延迟加载
 */
public class AccountTest {

    private InputStream in;
    private SqlSession session;
    private IAccountDao accountDao;

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
        accountDao = session.getMapper(IAccountDao.class);
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
     * 测试查询所有，并带有用户信息（Mybatis多表方式）
     */
    @Test
    public void testFindAll() {
        List<Account> accounts = accountDao.findAll();

        // 注释掉遍历，通过控制台日志就能看到只执行了 select * from account 语句
        /*for (Account account : accounts) {
            // System.out.println(account + "\n    " + account.getUser());
        }*/
    }

}
