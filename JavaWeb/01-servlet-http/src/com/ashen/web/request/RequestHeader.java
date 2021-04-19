package com.ashen.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/*
request功能：获取请求消息数据
2. 获取请求头数据
* 方法：
    * (重要)String getHeader(String name):通过请求头的名称获取请求头的值
    * Enumeration<String> getHeaderNames():获取所有的请求头名称
 */
@WebServlet("/req_header")
public class RequestHeader extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 演示获取请求头数据

        // 1.获取所有请求头名称
        Enumeration<String> headerNames = request.getHeaderNames();
        // 2.遍历
        while (headerNames.hasMoreElements()) {
            // 获取每一个请求头名称
            String name = headerNames.nextElement();
            // 根据请求头名称，获取对应值
            String value = request.getHeader(name);
            System.out.println(name + " = "+ value);
        }

        System.out.println("======================");

        // 获取指定请求头的值

        // 用户浏览器 user-agent
        String user_agent = request.getHeader("user-agent");

        System.out.println(user_agent);
        if (user_agent.contains("Chrome")) {
            System.out.println("你用的谷歌浏览器");
        } else if (user_agent.contains("Firefox")) {
            System.out.println("你用的火狐浏览器");
        }

        // 请求来源 referer
        String referer = request.getHeader("referer");
        System.out.println(referer); // http://localhost:8080/servlet/referer.html

        response.setContentType("text/html;charset=utf-8");

        if (referer != null && referer.contains("_01_Servlet")) {
            System.out.println("正常访问");
            response.getWriter().write("欢迎访问");
        } else {
            System.out.println("盗链来的");
            response.getWriter().write("别盗链，支持正版");
        }

    }
}
