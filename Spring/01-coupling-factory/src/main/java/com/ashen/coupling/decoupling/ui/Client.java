package com.ashen.coupling.decoupling.ui;

import com.ashen.coupling.decoupling.factory.BeanFactory;
import com.ashen.coupling.decoupling.service.IUserService;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            // IUserService service = new UserServiceImpl();

            // 使用工厂模式获取bean
            IUserService service = (IUserService) BeanFactory.getBean("userService");

            System.out.println(service);
            service.saveUser();
        }
    }
}
