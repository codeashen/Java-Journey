package com.ashen.ioc.anno.ui;

import com.ashen.ioc.anno.service.IUserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Cilent {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        IUserService userService = context.getBean("userService", IUserService.class);

        userService.saveUser();
        context.close(); // 关闭容器，单例bean销毁
    }
}
