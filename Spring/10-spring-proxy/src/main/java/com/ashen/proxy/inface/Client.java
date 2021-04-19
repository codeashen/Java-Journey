package com.ashen.proxy.inface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 一、基于接口的动态代理
 * 模拟经销商卖产品（动态代理加强销售方法）
 */
public class Client {

    public static void main(String[] args) {

        /* 匿名内部类访问外部成员变量时，外部成员变量要求是final的，
           Java8在这种情况默认外部成员变量是final的 */
        final Producer producer = new Producer();  // 被代理对象

        /**
         * 动态代理：
         *  特点：字节码随用随创建，随用随加载
         *  作用：不修改源码的基础上对方法增强
         *  分类：
         *      基于接口的动态代理
         *      基于子类的动态代理
         *  基于接口的动态代理：
         *      涉及的类：Proxy
         *      提供者：JDK官方
         *  如何创建代理对象：
         *      使用Proxy类中的newProxyInstance方法
         *  创建代理对象的要求：
         *      被代理类最少实现一个接口，如果没有则不能使用
         *  newProxyInstance方法的参数：
         *      ClassLoader：类加载器
         *          它是用于加载代理对象字节码的。和被代理对象使用相同的类加载器。[固定写法]。
         *      Class[]：字节码数组
         *          它是用于让代理对象和被代理对象有相同方法。[固定写法]。
         *      InvocationHandler：用于提供增强的代码
         *          它是让我们写如何代理。我们一般都是些一个该接口的实现类，通常情况下都是匿名内部类，但不是必须的。
         *          此接口的实现类都是谁用谁写，重写接口中的invoke方法，被代理对象接口上的任何方法都会经过该方法。
         */
        IProducer proxyProducer = (IProducer) Proxy.newProxyInstance(
                producer.getClass().getClassLoader(),
                producer.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 作用：执行被代理对象的任何接口方法都会经过该方法
                     * @param proxy   代理对象的引用         （不常用）
                     * @param method  当前执行(被拦截)的方法
                     * @param args    当前执行的方法的参数数组
                     * @return Object类型
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 判断拦截的方法是为需要增强的方法（此处为销售方法）
                        if ("saleProduce".equals(method.getName())) {
                            // 获取方法参数
                            float money = (float) args[0];
                            System.out.println("java经销商代销售产品，收取20%产品价格费用：" + money * 0.2f);
                            // 再执行原始方法内容
                            return method.invoke(producer, money * 0.8f);
                        } else {
                            // invoke方法  参数一：执行方法的对象，此处应该是被代理的源对象；参数二：方法参数Object数组
                            return method.invoke(producer, args);  // 不做加强
                        }
                    }
                });

        proxyProducer.saleProduce(1000);
        proxyProducer.afterService(50);
    }
}
