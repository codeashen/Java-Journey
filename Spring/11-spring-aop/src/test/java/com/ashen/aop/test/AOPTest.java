package com.ashen.aop.test;

import com.ashen.aop.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试AOP配置
 */
public class AOPTest {

    public static void main(String[] args) {
        // 1.创建容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 2.获取对象
        IAccountService service = context.getBean("accountService", IAccountService.class);
        // 3.执行方法
        service.saveAccount();
        service.updateAccount(5);
        service.deleteAccount();
    }
}
