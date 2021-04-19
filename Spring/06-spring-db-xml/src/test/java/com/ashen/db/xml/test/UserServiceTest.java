package com.ashen.db.xml.test;

import com.ashen.db.xml.domain.User;
import com.ashen.db.xml.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * 使用Junit单元测试：xml配置的dbutils操作
 *
 * 使用Junit单元测试：测试我们的配置
 * Spring整合junit的配置
 *      1、导入spring整合junit的jar(坐标)
 *      2、使用Junit提供的一个注解把原有的main方法替换了，替换成spring提供的
*             @Runwith
 *      3、告知spring的运行器，spring和ioc创建是基于xml还是注解的，并且说明位置
*             @ContextConfiguration
 *                locations：指定xml文件的位置，加上classpath关键字，表示在类路径下
 *                classes：指定注解类所在地位置
 *
 *   当我们使用spring 5.x版本的时候，要求junit的jar必须是4.12及以上
 */
@RunWith(SpringJUnit4ClassRunner.class)  // 由Spring的执行器执行测试方法
@ContextConfiguration(locations = "classpath:bean.xml")  // 指定Spring配置
public class UserServiceTest {

    @Autowired
    private IUserService userService;
    
    @Test
    public void testFindAll() {
        // 1.获取容器
        // ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 2.获取service对象
        // IUserService userService = context.getBean("userService", IUserService.class);

        /* Spring整合了Junit，userService可以注入，无需上述代码 */

        // 3.执行方法
        List<User> users = userService.findAllUser();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindById() {
        // 3.执行方法
        User user = userService.findUserById(41);
        System.out.println(user);
    }

    @Test
    public void testSave() {
        // 3.执行方法
        User user = new User();
        user.setUsername("韩梅梅");
        user.setBirthday(new Date());
        user.setSex("女");
        userService.saveUser(user);
    }

    @Test
    public void testUpdate() {
        // 3.执行方法
        User user = new User();
        user.setId(54);
        user.setUsername("李雷");
        userService.updateUser(user);
    }

    @Test
    public void testDelete() {
        // 3.执行方法
        userService.deleteUser(55);
    }
}
