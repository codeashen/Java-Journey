package com.ashen.aopanno.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 用于记录日志的工具类，它里面提供了公共的代码，这里作为AOP通知类
 * 相关注解：
 *      Aspect：         配置切面类，用在类上，表示这是一个切面类
 *      Pointcut：       配置切入点，用在空返回值空参空方法体方法上，value是切入点表达式
 *      Before：
 *      AfterReturning：
 *      AfterThrowing：
 *      After：
 *      Around：         以上5个用来配置切面，用在通知方法上，结合通知和切入点
 *                       属性值可以是切入点表达式，也可以是配好的切入点（要带括号）
 */
@Component("logger")
@Aspect   // 表示这是一个切面类
public class Logger {

    /**
     * 配置切入点（这是个空参数）
     */
    @Pointcut("execution(* com.ashen.aopanno.service.impl.*.*(..))")
    private void pt1(){}
    @Pointcut("execution(* com.ashen.aopanno.service.impl.AccountServiceImpl.updateAccount(..))")
    private void pt2(){}
    @Pointcut("execution(* com.ashen.aopanno.service.impl.AccountServiceImpl.deleteAccount(..))")
    private void pt3(){}

    /**
     * 前置通知
     */
    @Before("pt2()")
    public void beforePrintLog() {
        System.out.println("前置通知Logger类中的beforePrintLog方法开始记录日志了。。。");
    }

    /**
     * 后置通知
     */
    @AfterReturning("pt2()")
    public void afterReturningPrintLog() {
        System.out.println("后置通知Logger类中的afterReturningPrintLog方法开始记录日志了。。。");
    }

    /**
     * 异常通知
     */
    @AfterThrowing("pt2()")
    public void afterThrowingPrintLog() {
        System.out.println("异常通知Logger类中的afterThrowingPrintLog方法开始记录日志了。。。");
    }

    /**
     * 最终通知
     */
    @After("pt2()")
    public void afterPrintLog() {
        System.out.println("最终通知Logger类中的afterPrintLog方法开始记录日志了。。。");
    }

    /**
     * 环绕通知
     */
    @Around("pt3()")
    public Object aroundPrintLog(ProceedingJoinPoint point) {
        Object result = null;  // 返回值
        try {
            Object[] args = point.getArgs();  // 获取切入点方法的参数
            System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。前置");
            result = point.proceed(args);  // 明确调用业务层方法（切入点方法）
            System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。后置");
            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。异常");
        } finally {
            System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。最终");
        }
        return result;
    }
}
