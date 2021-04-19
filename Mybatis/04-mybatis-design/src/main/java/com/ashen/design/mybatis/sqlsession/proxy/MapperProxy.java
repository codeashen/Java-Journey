package com.ashen.design.mybatis.sqlsession.proxy;

import com.ashen.design.mybatis.cfg.Mapper;
import com.ashen.design.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

public class MapperProxy implements InvocationHandler {

    // Map的key是 全类名+方法名
    private Map<String, Mapper> mappers;
    private Connection conn;

    public MapperProxy(Map<String, Mapper> mappers, Connection conn) {
        this.mappers = mappers;
        this.conn = conn;
    }

    /**
     * 执行被代理方法的任何方法都会经过该方法。可以在此处进行增强，实现功能。
     * 用于对方法进行增强，此处的增强就是调用selectList方法
     * @param proxy     代理对象的引用
     * @param method    当前执行的方法
     * @param args      当前执行的方法所需的参数
     * @return          和被增强的方法具有相同的返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 1.获取方法名
        String methodName = method.getName();
        // 2.获取声明该方法的类名（本例子中应该的dao接口名）
        String className = method.getDeclaringClass().getName();
        // 3.组合key
        String key = className + "." + methodName;
        // 4.获取mappers中对应的Mapper对象
        Mapper mapper = mappers.get(key);
        // 5.判断是否存在Mapper
        if (mapper == null) {
            throw new IllegalArgumentException("传入的参数有误");
        }
        // 6.调用工具类，执行Mapper对象中的sql
        return new Executor().selectList(mapper, conn);
    }
}
