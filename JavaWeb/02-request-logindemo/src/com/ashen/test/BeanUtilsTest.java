package com.ashen.test;

import com.ashen.domain.User;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class BeanUtilsTest {

    @Test
    public void BeanUtilsTest() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        User user = new User();
        Map<String, String> userMap = Map.of("username", "Alice", "password", "123456");

        BeanUtils.populate(user, userMap);

        BeanUtils.setProperty(user, "id", 5);

        BeanUtils.getProperty(user, "username");

        System.out.println(user);


    }
}
