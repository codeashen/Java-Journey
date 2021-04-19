package com.ashen.web.servlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * GenericServlet是抽象类，实现了Servlet接口
 * 其中service方法还是抽象方法，接口中其他抽象方法做了空实现
 *
 * Servlet体系结构：
 *
 * Servlet接口
 *   |--GenericServlet抽象类
 *          |--HttpServlet抽象类
 */
@WebServlet("/genericServlet")
public class GenericServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("GenericServlet测试。。。");
    }
}
