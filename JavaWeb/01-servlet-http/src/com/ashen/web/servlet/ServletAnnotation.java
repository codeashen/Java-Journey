package com.ashen.web.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Servlet注解配置
 * Servlet3.0之后，支持注解配置，可以不用创建 web.xml 文件了
 */
//@WebServlet(urlPatterns = {"/annotation", "anno"})    // 配置urlpattern，可以配置多个
//@WebServlet(value = "/annotation")    // 作用于urlpatterns等效
@WebServlet("/anno")     // 当注解中只有value一个属性时，省略属性名
public class ServletAnnotation implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("注解配置的service方法执行...");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
