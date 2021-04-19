package com.ashen.usercenter;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserCenterApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(1111);
        Assert.assertFalse(1 == 2);
    }

}
