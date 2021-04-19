package com.ashen.design.pattern.structural.proxy.dynamiccglib;

import com.ashen.design.pattern.structural.proxy.Order;
import com.ashen.design.pattern.structural.proxy.db.DataSourceContextHolder;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class OrderServiceCglibProxy implements MethodInterceptor {

    private Object target;

    public OrderServiceCglibProxy(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Enhancer.create(target.getClass(), this);
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        before(args[0]);
        method.invoke(target, args);
        after();
        return null;
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
