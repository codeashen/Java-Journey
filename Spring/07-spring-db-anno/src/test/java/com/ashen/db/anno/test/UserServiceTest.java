package com.ashen.db.anno.test;

import com.ashen.db.anno.domain.User;
import com.ashen.db.anno.service.IUserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

/**
 * 使用Junit单元测试：xml配置的dbutils操作
 */
public class UserServiceTest {
    
    @Test
    public void testFindAll() {
        // 1.获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 2.获取service对象
        IUserService userService = context.getBean("userService", IUserService.class);
        // 3.执行方法
        List<User> users = userService.findAllUser();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindById() {
        // 1.获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 2.获取service对象
        IUserService userService = context.getBean("userService", IUserService.class);
        // 3.执行方法
        User user = userService.findUserById(41);
        System.out.println(user);
    }

    @Test
    public void testSave() {
        // 1.获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 2.获取service对象
        IUserService userService = context.getBean("userService", IUserService.class);
        // 3.执行方法
        User user = new User();
        user.setUsername("韩梅梅");
        user.setBirthday(new Date());
        user.setSex("女");
        userService.saveUser(user);
    }

    @Test
    public void testUpdate() {
        // 1.获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 2.获取service对象
        IUserService userService = context.getBean("userService", IUserService.class);
        // 3.执行方法
        User user = new User();
        user.setId(54);
        user.setUsername("李雷");
        userService.updateUser(user);
    }

    @Test
    public void testDelete() {
        // 1.获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 2.获取service对象
        IUserService userService = context.getBean("userService", IUserService.class);
        // 3.执行方法
        userService.deleteUser(55);
    }
}
