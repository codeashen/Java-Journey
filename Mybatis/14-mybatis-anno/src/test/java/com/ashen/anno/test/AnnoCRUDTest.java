package com.ashen.anno.test;

import com.ashen.anno.dao.IUserDao;
import com.ashen.anno.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * 注解CRUD测试
 */
public class AnnoCRUDTest {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }

    @Test
    public void test() throws Exception {
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("Giao桑");
        user.setAddress("湖北武汉");

        userDao.saveUser(user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(50);
        user.setUsername("王尼玛");
        user.setAddress("江苏南京");
        user.setSex("男");
        user.setBirthday(new Date());

        userDao.updateUser(user);
    }


    @Test
    public void testDelete(){
        userDao.deleteUser(51);
    }

    @Test
    public void testFindOne(){
        User user = userDao.findById(50);
        System.out.println(user);
    }


    @Test
    public  void testFindByName(){
        List<User> users = userDao.findUserByName("%王%");
        // List<User> users = userDao.findUserByName("mybatis");
        for(User user : users){
            System.out.println(user);
        }
    }

    @Test
    public  void testFindTotal(){
        int total = userDao.findTotalUser();
        System.out.println(total);
    }

}
