package com.ashen.aopanno.test;

import com.ashen.aopanno.service.IAccountService;
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
        service.updateAccount(5); //测试单独的通知（注解配置的最终通知和后置通知会有问题）
        service.deleteAccount();    //测试环绕通知（注解配置时手写环绕通知，不会出现顺序问题）
    }
}
