package com.ashen.aoptx.xml.test;

import com.ashen.aoptx.xml.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 使用Junit单元测试：事务控制的转账操作
 * 目的是为了引出动态代理，进而引出AOP
 */
@RunWith(SpringJUnit4ClassRunner.class)  // 由Spring的执行器执行测试方法
@ContextConfiguration(locations = "classpath:bean.xml")  // 指定Spring配置
public class AccountServiceTest {

    @Autowired
    private IAccountService accountService;

    /**
     * 测试转账
     */
    @Test
    public void transferTest() {
        accountService.transfer(1, 2, 100d);
    }
}
