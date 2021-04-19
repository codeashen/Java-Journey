package com.ashen.design.pattern.structural.proxy;

/**
 * 被例中的被代理类，要求：调用Dao前切换数据源
 */
public class OrderServiceImpl implements IOrderService {

    private IOrderDao orderDao;

    @Override
    public int saveOrder(Order order) {
        System.out.println("Service层调用Dao层添加Order");
        orderDao = new OrderDaoImpl();  // 实际中由Spring注入
        return orderDao.insert(order);
    }
}
