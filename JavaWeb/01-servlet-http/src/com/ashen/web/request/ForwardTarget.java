package com.ashen.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/*
共享数据：
    * 域对象：一个有作用范围的对象，可以在范围内共享数据
    * request域：代表一次请求的范围，一般用于请求转发的多个资源中共享数据
    * 方法：
        1. void setAttribute(String name,Object obj):存储数据
        2. Object getAttitude(String name):通过键获取值
        3. void removeAttribute(String name):通过键移除键值对
        4. Enumeration<E> getAttributeNames():获取request域中所有属性名的Enumeration
 */
@WebServlet("/forword_target")
public class ForwardTarget extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("这里是请求转发目标Servlet");

        // 获取request域中指定属性
        Object name = request.getAttribute("name");
        System.out.println("name = " + name);

        System.out.println("---------------------------------");

        // 获取request域中所有属性名
        Enumeration<String> attrNames = request.getAttributeNames();
        while (attrNames.hasMoreElements()) {
            String attrName = attrNames.nextElement();
            Object attribute = request.getAttribute(attrName);

            System.out.println(attrName + " = " + attribute);
        }
        /*
        输出不只是name和age，说明request域中本来就有一些属性
        javax._01_Servlet.forward.request_uri = /_01_Servlet/forword
        javax._01_Servlet.forward.context_path = /_01_Servlet
        javax._01_Servlet.forward.servlet_path = /forword
        javax._01_Servlet.forward.mapping = org.apache.catalina.core.ApplicationMapping$MappingImpl@21249d5f
        name = 张三
        age = 18
         */
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
