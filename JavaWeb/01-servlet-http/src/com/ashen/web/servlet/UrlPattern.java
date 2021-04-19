package com.ashen.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * urlpattern:Servlet访问路径
 * 1. 一个Servlet可以定义多个访问路径 ： @WebServlet({"/d4","/dd4","/ddd4"})
 * 2. 路径定义规则：
 *      1) /xxx：路径匹配
 * 		2) /xxx/xxx:多层路径，目录结构
 * 		3) *.do：扩展名匹配
 * 3.通配符*的使用：
 *      1) /*
 *      2) /xxx/*
 *      3) *.do
 */
//@WebServlet({"/pt1", "/pt2", "/pt3"})
//@WebServlet("/_01_Servlet/partern")
@WebServlet("/pattern/*")
//@WebServlet("/dodo")    //有后缀名的样式，前面不要加斜杠，其他的要加斜杠
public class UrlPattern extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("urlpattern规则合法...");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
