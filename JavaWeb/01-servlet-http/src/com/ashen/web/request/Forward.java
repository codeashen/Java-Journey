package com.ashen.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
请求转发：一种在服务器内部的资源跳转方式
    1. 步骤：
        1. 通过request对象获取请求转发器对象：RequestDispatcher getRequestDispatcher(String path)
        2. 使用RequestDispatcher对象来进行转发：forward(ServletRequest _02_HttpServletRequest, ServletResponse response)

    2. 特点：
        1. 浏览器地址栏路径不发生变化
        2. 只能转发到当前服务器内部资源中。
        3. 转发是一次请求

共享数据：
    * 域对象：一个有作用范围的对象，可以在范围内共享数据
    * request域：代表一次请求的范围，一般用于请求转发的多个资源中共享数据
    * 方法：
        1. void setAttribute(String name,Object obj):存储数据
        2. Object getAttitude(String name):通过键获取值
        3. void removeAttribute(String name):通过键移除键值对
        4. Enumeration<E> getAttributeNames():获取request域中所有属性名的Enumeration
 */
@WebServlet("/forword")
public class Forward extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("转发前的资源被访问了");

        // request域，共享数据
        request.setAttribute("name", "张三");
        request.setAttribute("age", 18);


        /*
        转发到  静态资源、JSP、Servlet  都可以
        前面加不加斜杠都可以
        访问路径是虚拟路径下的资源，即request.getContextPath()获取到的路径   /_01_Servlet
         */
        // _02_HttpServletRequest.getRequestDispatcher("register.html").forward(_02_HttpServletRequest, response); // 静态资源
        // _02_HttpServletRequest.getRequestDispatcher("index.jsp").forward(_02_HttpServletRequest, response); // JSP
        request.getRequestDispatcher("forword_target").forward(request, response); // Servlet


        System.out.println("之后的代码还会继续执行");
        /*
        无论是 _02_HttpServletRequest.getRequestDispatcher(path).forward(_02_HttpServletRequest, response)还是response.sendRedirect,
        程序都会在执行完该句的情况下继续向下执行,因此在必要的时候应该使用return终止该方法.

        对于 _02_HttpServletRequest.getRequestDispatcher(path).forward(_02_HttpServletRequest, response),在执行完该方法的时候再进行对
        request的操作已经没有任何意义,如果在该方法之后再进行request.setAttribute(),该值将不会被放进当前请求
        的request中.

        response.setRedirect:该方法执行之后,接下来的方法也会被执行.但是使用该方法的时候,会发送一个全新的
        _02_HttpServletRequest,将不再使用原先的request,因此不论在该方法执行之前,还是在该方法执行之后,对request操作,都是无效的.
         */

    }
}
