package com.ashen.authority.config;

import com.ashen.authority.controller.SysLogController;
import com.ashen.authority.domain.SysLog;
import com.ashen.authority.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 日志切面类，Aspect注解标志为一个切面类
 * 拦截controller包下所有类的所有方法，记录日志
 */
@Aspect
@Component
public class LogAop {

    // 访问controller的时间
    private Date visitTime;
    // 访问的类
    private Class executionClass;
    // 访问的方法
    private Method executionMethod;

    /**
     * 注入HttpServletRequest对象，以在doAfter方法中获取访问的ip
     */
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    /**
     * 前置通知
     * 获取 1、访问时间，2、访问的类，3、访问的方法
     * @param point
     */
    @Before("execution(* com.ashen.authority.controller.*.*(..))")
    public void doBefore(JoinPoint point) throws NoSuchMethodException {
        // 当前时间就是访问开始的时间
        visitTime = new Date();

        // 获取aop目标，即目标类
        executionClass = point.getTarget().getClass();

        // 获取aop连接点方法名
        String methodName = point.getSignature().getName();
        // 获取方法参数
        Object[] args = point.getArgs();
        // 获取目标方法
        if (args == null || args.length == 0) {
            // 无参数的方法
            executionMethod = executionClass.getMethod(methodName);
        } else {
            // 有参数，就将args中所有元素遍历，获取对应的Class,装入到一个Class[]
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            executionMethod = executionClass.getMethod(methodName, classArgs);
        }
    }

    /**
     * 后置通知
     * 获取 1、访问时长，2、访问的URL，3、访问ip地址，4、操作者用户名
     * @param point
     */
    @After("execution(* com.ashen.authority.controller.*.*(..))")
    public void doAfter(JoinPoint point) {
        // 1、获取访问时长
        long time = new Date().getTime() - visitTime.getTime();

        // 2、获取访问URL
        String url = "";
        if (executionClass != null && executionMethod != null
                && executionClass != SysLogController.class) {
            // 获取类上的@RequestMapping对象
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                // 获取方法上的@RequestMapping对象
                RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    // 获取url，值应该是类上的@RequestMapping的value+方法上的@RequestMapping的value
                    url = classAnnotation.value()[0] + methodAnnotation.value()[0];
                }
            }
        }

        // 3、获取访问ip地址
        String remoteAddr = request.getRemoteAddr();

        // 4、获取操作者用户名
        /*
        需要获取Spring Security 的上下文对象SecurityContext，从中获取User，
        上下文对象可以通过securityContextHolder获取，也可以从request.getSession中获取
         */
        // 4.1 获取SecurityContext
        SecurityContext context = SecurityContextHolder.getContext();  // 也可以 request.getSession().getAttribute("PRING_SECURITY_CONTEXT");
        // 4.2 获取User（Spring Security 的User）
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        // 创建SysLog对象，封装属性
        SysLog sysLog = new SysLog();
        sysLog.setVisitTime(visitTime);
        sysLog.setUsername(username);
        sysLog.setIp(remoteAddr);
        sysLog.setUrl(url);
        sysLog.setExecutionTime(time);
        sysLog.setMethod("[类名]" + executionClass.getName() + " [方法名]" + executionMethod.getName());

        sysLogService.save(sysLog);
    }

}
