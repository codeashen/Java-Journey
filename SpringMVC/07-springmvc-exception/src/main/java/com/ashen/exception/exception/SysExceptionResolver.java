package com.ashen.exception.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 自定义异常处理器
 * 1. 实现HandlerExceptionResolver
 * 2. 配置到IoC容器中即可
 */
public class SysExceptionResolver implements HandlerExceptionResolver {

    /**
     *
     * @param request Http请求
     * @param response Http响应
     * @param handler 表示当前处理器对象（不常用）
     * @param ex 拦截到的异常
     * @return 返回ModelAndView对象
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        SysException e = null;
        if (ex instanceof SysException) {
            e = (SysException) ex;
        } else {
            e = new SysException("未知错误...");
        }

        // 创建ModelAndView对象
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorMsg", e.getMessage());
        return mv;
    }
}
