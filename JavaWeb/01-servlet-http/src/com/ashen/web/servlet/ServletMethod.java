package com.ashen.web.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * Servlet接口，抽象方法介绍
 */
public class ServletMethod implements Servlet {

    /**
     * 初始化方法
     * 在Servlet被创建时执行，只执行一次
     * @param servletConfig
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init....");
    }

    /**
     * 获取ServletConfig对象
     * @return ServletConfig Servlet的配置对象（了解）
     */
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 提供服务的方法
     * 每一次Servlet被访问时，执行，执行多次
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service...");
    }

    /**
     * 获取Servlet信息，版本、作者等（了解）
     * @return
     */
    @Override
    public String getServletInfo() {
        return null;
    }

    /**
     * 销毁方法
     * 在服务器正常关闭时，执行，执行一次
     */
    @Override
    public void destroy() {
        System.out.println("destory...");
    }
}
