package com.ashen.web.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
ServletContext对象：
1. 概念：代表整个web应用，可以和程序的容器(服务器)来通信
2. 获取：
    1. 通过request对象获取
        request.getServletContext();
    2. 通过HttpServlet获取
        this.getServletContext();
3. 功能：
    1. 获取MIME类型：
    2. 域对象：共享数据
    3. 获取文件的真实(服务器)路径
 */
@WebServlet("/getServletContext")
public class ServletContext extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1.通过request获取
        ServletContext servletContext1 = request.getServletContext();
        // 2.通过HttpServlet获取
        ServletContext servletContext2 = this.getServletContext();

        System.out.println(servletContext1);
        System.out.println(servletContext2);

        System.out.println(servletContext1 == servletContext2);  //true
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
