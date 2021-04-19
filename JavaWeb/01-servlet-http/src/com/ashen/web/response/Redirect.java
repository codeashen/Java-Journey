package com.ashen.web.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
重定向的特点:redirect
    1. 地址栏发生变化
    2. 重定向可以访问其他站点(服务器)的资源
    3. 重定向是两次请求。不能使用request对象来共享数据
转发的特点：forward
    1. 转发地址栏路径不变
    2. 转发只能访问当前服务器下的资源
    3. 转发是一次请求，可以使用request对象来共享数据
 */
@WebServlet("/redirect")
public class Redirect extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("重定向前Servlet");

        /*
        // 请求转发的原始操作
        // 1.设置响应状态码为 302
        response.setStatus(302);
        // 2.设置响应头的location
        response.setHeader("location", "/servlet/red_target");
        */

        // 重定向简单写法
        response.sendRedirect("/servlet/red_target");

        // 重定向可以访问其他站点的资源
        // response.sendRedirect("http://www.itcast.com");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
