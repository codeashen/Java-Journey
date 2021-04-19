package com.ashen.bean.ui;

import com.ashen.bean.service.IUserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {

    public static void main(String[] args) {
        // 1.获取核心容器对象
        // ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 不使用多态，才能使用子类的close方法关闭容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        // 2.根据id获取Bean对象
        IUserService service1 = context.getBean("userService", IUserService.class);
        IUserService service2 = context.getBean("userService", IUserService.class);
        service1.saveUser();

        System.out.println(service1);
        System.out.println(service2);
        System.out.println(service1 == service2);  // scope为singleton时true，为prototype时false

        // 3.关闭Spring容器，所有单例Bean销毁
        context.close();   // ApplicationContext接口中没有close方法，实现类才有
    }

}
