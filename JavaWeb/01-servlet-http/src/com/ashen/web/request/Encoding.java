package com.ashen.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
中文乱码问题：
    * get方式：tomcat 8 已经将get方式乱码问题解决了
    * post方式：会乱码
        * 解决：在获取参数前，设置request的编码request.setCharacterEncoding("utf-8");
 */
@WebServlet("/encoding")
public class Encoding extends HttpServlet {
    // 使用register.html 演示
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 设置流的编码
        request.setCharacterEncoding("utf-8");

        String username = request.getParameter("username");
        System.out.println(username);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
