package com.ashen.design.pattern.structural.proxy.staticproxy;

import com.ashen.design.pattern.structural.proxy.OrderServiceImpl;
import com.ashen.design.pattern.structural.proxy.IOrderService;
import com.ashen.design.pattern.structural.proxy.Order;
import com.ashen.design.pattern.structural.proxy.db.DataSourceContextHolder;

public class OrderServiceStaticProxy {

    private IOrderService orderService;

    public int saveOrder(Order order) {
        orderService = new OrderServiceImpl();  // 实际中又Spring注入

        before(order);
        orderService.saveOrder(order);
        after();
        return 0;
    }

    private void before(Order order) {
        System.out.println("====== before method ======");
        // 模拟增强方法，数据库切换
        int userId = order.getUserId();
        int dbRouter = userId % 2;
        System.out.println("静态代理分配到【db" + dbRouter + "】处理数据");
        // todo 设置DataSource
        DataSourceContextHolder.setDBType("db" + dbRouter);
    }

    private void after() {
        System.out.println("====== after method ======");
    }
}
