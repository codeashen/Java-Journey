package com.ashen.di.ui;

import com.ashen.di.service.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {

    public static void main(String[] args) {
        // 1.获取核心容器对象
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 2.根据id获取Bean对象

        // 构造方法注入属性
        IUserService service = context.getBean("userService", IUserService.class);
        service.saveUser();
        System.out.println("----------------------");

        // setter方法注入属性
        IUserService service2 = context.getBean("userService2", IUserService.class);
        service2.saveUser();
        System.out.println("----------------------");

        // 复杂类型/集合类型注入
        IUserService service3 = context.getBean("userService3", IUserService.class);
        service3.saveUser();
    }

}
