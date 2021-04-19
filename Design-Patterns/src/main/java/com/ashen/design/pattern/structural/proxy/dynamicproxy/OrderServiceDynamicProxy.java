package com.ashen.design.pattern.structural.proxy.dynamicproxy;

import com.ashen.design.pattern.structural.proxy.Order;
import com.ashen.design.pattern.structural.proxy.db.DataSourceContextHolder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class OrderServiceDynamicProxy implements InvocationHandler {

    private Object target;

    public OrderServiceDynamicProxy(Object target) {
        this.target = target;
    }

    /**
     * 获取代理对象
     */
    public Object getProxyInstance() {
        Class<?> clazz = target.getClass();
        // Proxy类创建代理对象方法，参数：类加载器，类上的接口，InvocationHandler对象
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    /**
     * 代理对象的代理方法
     * @param proxy  代理对象
     * @param method 被代理方法
     * @param args   被代理方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before(args[0]);
        Object ret = method.invoke(target, args);
        after();
        return ret;
    }

    private void before(Object obj) {
        System.out.println("====== before method ======");
        int userId = 0;
        if (obj instanceof Order) {
            Order order = (Order) obj;
            userId = order.getUserId();
        }

        int dbRouter = userId % 2;
        System.out.println("静态代理分配到【db" + dbRouter + "】处理数据");
        // todo 设置dataSource
        DataSourceContextHolder.setDBType("db" + dbRouter);
    }

    private void after() {
        System.out.println("====== after method ======");
    }

}
