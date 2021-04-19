package com.ashen;

import com.ashen.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 模拟启动服务提供者
 */
public class Provider {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("provider.xml");
        UserService userService = (UserService) context.getBean("userService");
        userService.getUserAddressList("1");

        System.in.read();  // 阻塞，不让方法结束
    }

}