package com.ashen.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HttpServlet中没有抽象方法，不重写方法不报错，但是他确实是抽象类
 * 使用HttpServlet，应该重写 doGet 和 doPost 方法
 *
 * Servlet体系结构：
 *
 * Servlet接口
 *   |--GenericServlet抽象类
 *          |--HttpServlet抽象类
 */
@WebServlet("/httpServlet")
public class HttpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet方法执行...");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost方法执行...");
    }
}
