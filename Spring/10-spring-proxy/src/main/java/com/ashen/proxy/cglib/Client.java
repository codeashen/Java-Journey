package com.ashen.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 二、基于子类的动态代理
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
         *      涉及的类：Enhancer
         *      提供者：JDK官方
         *  如何创建代理对象：
         *      使用Enhancer类中的create方法
         *  创建代理对象的要求：
         *      被代理类不能是最终类
         *  newProxyInstance方法的参数：
         *      Class：字节码
         *          它是用于指定被代理对象的字节码。
         *      Callback：用于提供增强的代码
         *          它是让我们写如何代理。我们一般都是些一个该接口的实现类，通常情况下都是匿名内部类，但不是必须的。
         *          此接口的实现类都是谁用谁写，重写接口中的intercept方法，被代理对象接口上的任何方法都会经过该方法。
         *          我们一般写的都是该接口的子接口实现类：MethodInterceptor接口（方法拦截器）
         *             发现cglib里也有一个子接口      InvocationHandler接口 （用法和面向接口的动态代理相同）
         */
        Producer cglibProducer1 = (Producer) Enhancer.create(producer.getClass(),
                new MethodInterceptor() {
                    /**
                     *
                     * @param proxy   代理对象的引用   （不常用）
                     * @param method  当前被拦截的方法
                     * @param args    当前被拦截方法的参数数组
                     *      以上三个参数和基于接口的动态代理中invoke方法的参数是一样的
                     * @param methodProxy   当前被拦截方法的代理对象  (更不常用)
                     * @return Object类型
                     * @throws Throwable
                     */
                    @Override
                    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                        // 判断拦截的方法是为需要增强的方法（此处为销售方法）
                        if ("saleProduce".equals(method.getName())) {
                            // 获取方法参数
                            float money = (float) args[0];
                            System.out.println("cglib一号经销商代销售产品，收取20%产品价格费用：" + money * 0.2f);
                            // 再执行原始方法内容
                            return method.invoke(producer, money * 0.8f);
                        } else {
                            // invoke方法  参数一：执行方法的对象，此处应该是被代理的源对象；参数二：方法参数Object数组
                            return method.invoke(producer, args);  // 不做加强
                        }
                    }
                });

        Producer cglibProducer2 = (Producer) Enhancer.create(Producer.class, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 判断拦截的方法是为需要增强的方法（此处为销售方法）
                if ("saleProduce".equals(method.getName())) {
                    // 获取方法参数
                    float money = (float) args[0];
                    System.out.println("cglib二号经销商代销售产品，收取15%产品价格费用：" + money * 0.15f);
                    // 再执行原始方法内容
                    return method.invoke(producer, money * 0.85f);
                } else {
                    // invoke方法  参数一：执行方法的对象，此处应该是被代理的源对象；参数二：方法参数Object数组
                    return method.invoke(producer, args);  // 不做加强
                }
            }
        });

        cglibProducer1.saleProduce(1000);
        cglibProducer1.afterService(50);

        cglibProducer2.saleProduce(1000);
    }
}
