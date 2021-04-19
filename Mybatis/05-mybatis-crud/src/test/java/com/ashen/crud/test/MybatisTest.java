package com.ashen.crud.test;

import com.ashen.crud.dao.IUserDao;
import com.ashen.crud.domain.QueryVo;
import com.ashen.crud.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * 测试Mybatis的CRUD操作
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
        session = factory.openSession(true);

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
        session.commit();
        // 7.释放资源
        session.close();
        in.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() {
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试保存用户
     * 是用了【selectKey】，回写用户id
     */
    @Test
    public void testSave() {
        User user = new User();
        user.setUserName("Lucy");
        user.setBirthday(new Date());
        user.setGender("女");
        user.setAddress("安徽宣城");

        System.out.println("插入操作前 id=" + user.getUserId());   // null
        userDao.saveUser(user);
        // 执行插入，并回写了id
        System.out.println("插入操作后 id=" + user.getUserId());   // 51
    }

    /**
     * 测试更新操作
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setUserId(45);
        user.setUserName("Bob");
        user.setBirthday(new Date());
        user.setGender("男");
        user.setAddress("江苏南京");

        userDao.updateUser(user);
    }

    /**
     * 测试删除方法
     */
    @Test
    public void testDelete() {
        userDao.deleteUserById(52);
    }

    /**
     * 测试查找一个
     */
    @Test
    public void testFindById() {
        User user = userDao.findById(41);
        System.out.println(user);
    }

    /**
     * 【模糊查询】测试模糊查询操作
     */
    @Test
    public void testFindByName() {
        // 方式一：传参数时模糊（用的是PreparedStatement预处理）
        List<User> users = userDao.findByName("%王%");
        // 方式二：sql配置中模糊（用的是Statement拼接参数，了解）
        // List<User> users = userDao.findByName("王");
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试查询总记录条数
     */
    @Test
    public void testFindTotal() {
        int count = userDao.findTotal();
        System.out.println(count);
    }

    /**
     * 测试使用QueryVo作为查询条件
     */
    @Test
    public void testFindByVo() {
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserName("老王");
        vo.setUser(user);

        List<User> users = userDao.findUserByVo(vo);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }
}
