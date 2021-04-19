package com.ashen.aoptx.anno.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 和事务管理相关的工具类，它包含了，开启事务，提交事务，回滚事务和释放连接
 */
@Component("txManager")
@Aspect  // 标注为通知类
public class TransactionManager {

    @Autowired
    private ConnectionUtils connectionUtils;

    // 注解配置切入点表达式
    @Pointcut("execution(* com.ashen.aoptx.anno.service.impl.*.*(..))")
    private void pt1(){}

    /**
     * 开启事务
     */
    // @Before("pt1()")
    public void beginTx() {
        try {
            // 关闭连接的自动提交
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    // @AfterReturning("pt1()")
    public void commit() {
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
    // @AfterThrowing("pt1()")
    public void rollback() {
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放资源
     */
    // @After("pt1()")
    public void release() {
        try {
            /* 还回连接池中，此时当前线程上还有连接，但是是一个closed Connection，
               所以要在解绑线程中的Connection */
            connectionUtils.getThreadConnection().close();
            connectionUtils.removeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 环绕通知，
     * 注解配置通知的顺序问题，最终通知在后置通知之前执行，所以使用注解配置，请示用环绕通知
     * @param point 明确调用切入点方法的对象
     * @return
     */
    @Around("pt1()")
    public Object aroundAdvice(ProceedingJoinPoint point) {
        Object[] args = point.getArgs();   // 切入点参数
        Object rtValue = null;             // 切入点返回值
        try {
            // 开启事务
            this.beginTx();
            // 执行切入点方法
            rtValue = point.proceed(args);
            // 提交事务
            this.commit();
        } catch (Throwable e) {
            // 回滚事务
            this.rollback();
        } finally {
            // 释放连接
            this.release();
        }
        return rtValue;
    }
}
