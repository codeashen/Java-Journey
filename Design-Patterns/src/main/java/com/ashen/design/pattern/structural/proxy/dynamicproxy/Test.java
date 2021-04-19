package com.ashen.design.pattern.structural.proxy.dynamicproxy;

import com.ashen.design.pattern.structural.proxy.IOrderService;
import com.ashen.design.pattern.structural.proxy.Order;
import com.ashen.design.pattern.structural.proxy.OrderServiceImpl;

public class Test {
    public static void main(String[] args) {
        // 创建代理对象
        IOrderService proxyOrderService = (IOrderService)
                new OrderServiceDynamicProxy(new OrderServiceImpl()).getProxyInstance();

        // 调用代理对象的方法
        Order order = new Order();
        order.setUserId(1);
        proxyOrderService.saveOrder(order);
    }
}
