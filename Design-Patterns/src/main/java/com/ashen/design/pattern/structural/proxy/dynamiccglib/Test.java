package com.ashen.design.pattern.structural.proxy.dynamiccglib;

import com.ashen.design.pattern.structural.proxy.IOrderService;
import com.ashen.design.pattern.structural.proxy.Order;
import com.ashen.design.pattern.structural.proxy.OrderServiceImpl;

public class Test {
    public static void main(String[] args) {
        IOrderService proxyOrderService = (IOrderService)
                new OrderServiceCglibProxy(new OrderServiceImpl()).getProxyInstance();

        // 调用代理对象的方法
        Order order = new Order();
        order.setUserId(1);
        proxyOrderService.saveOrder(order);
    }
}
