package com.ashen.web.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
路径写法：
1. 路径分类
    1. 相对路径：通过相对路径不可以确定唯一资源
        * 如：./index.html
        * 不以/开头，以.开头路径（或者省略./）

        * 规则：找到当前资源和目标资源之间的相对位置关系
            * ./：当前目录
            * ../:后退一级目录
    2. 绝对路径：通过绝对路径可以确定唯一资源
        * 如：http://localhost/day15/responseDemo2		/day15/responseDemo2 (这是绝对路径)
        * 以/开头的路径

要不要加虚拟目录
* 规则：判断定义的路径是给谁用的？判断请求将来从哪儿发出
    * 给客户端浏览器使用：需要加虚拟目录(项目的访问路径)
        * 建议虚拟目录动态获取：request.getContextPath()
        * 重定向、<a>、 <form>、等....
    * 给服务器使用：不需要加虚拟目录
        * 请求转发路径
 */
@WebServlet("/path")
public class Path extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 请求转发不需要加虚拟目录
        // request.getRequestDispatcher("/index.jsp").forward(request, response);

        // 重定向需要加虚拟目录，建议动态获取虚拟目录
        String contextPath = request.getContextPath();  // servlet
        response.sendRedirect(contextPath + "/index.jsp");

        // 静态页面中的<a>、<form action>都需要加虚拟目录
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
