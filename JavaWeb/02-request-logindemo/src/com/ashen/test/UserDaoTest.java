package com.ashen.test;

import com.ashen.dao.UserDao;
import com.ashen.domain.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void testLogin() {
        User loginUser = new User();
        loginUser.setUsername("zhangsan");
        loginUser.setPassword("错密码");

        UserDao dao = new UserDao();
        User user = dao.login(loginUser);

        System.out.println(user);
    }
}
