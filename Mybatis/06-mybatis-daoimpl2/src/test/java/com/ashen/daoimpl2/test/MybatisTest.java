package com.ashen.daoimpl2.test;

import com.ashen.daoimpl2.dao.IUserDao;
import com.ashen.daoimpl2.dao.impl.UserDaoImpl;
import com.ashen.daoimpl2.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * 测试Mybatis的CRUD操作（使用编写dao实现类方式）
 */
public class MybatisTest {

    private InputStream in;
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

        // 3.使用工厂对象创建dao实现类对象
        userDao = new UserDaoImpl(factory);

        // 4.测试类执行，dao实现类执行数据库操作
    }

    /**
     * 所有测试方法之后执行的方法，此处提交事务+释放资源
     */
    @After
    public void destory() throws Exception {
        // 5.释放资源
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
     */
    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("Lucy");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("安徽宣城");

        userDao.saveUser(user);
    }

    /**
     * 测试更新操作
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(45);
        user.setUsername("Bob");
        user.setBirthday(new Date());
        user.setSex("男");
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
     * 测试模糊查询操作
     */
    @Test
    public void testFindByName() {
        List<User> users = userDao.findByName("%王%");
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

}
