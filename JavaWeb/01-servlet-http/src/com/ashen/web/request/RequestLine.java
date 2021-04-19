package com.ashen.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
request功能：获取请求消息数据
1. 获取请求行数据
    * GET /day14/demo1?name=zhangsan HTTP/1.1
* 方法：
    1. 获取请求方式 ：GET
        * String getMethod()
    2. (重要)获取虚拟目录：/_01_Servlet
        * String getContextPath()
    3. 获取Servlet路径: /req_line
        * String getServletPath()
    4. 获取get方式请求参数：name=zhangsan
        * String getQueryString()
    5. (重要)获取请求URI：/_01_Servlet/req_line
        * String getRequestURI()        :   /_01_Servlet/req_line
        * StringBuffer getRequestURL()  :   http://localhost:8080/servlet/req_line

        * URL:统一资源定位符   ： http://localhost:8080/servlet/req_line	    中华人民共和国
        * URI：统一资源标识符  :  /_01_Servlet/req_line					        共和国

    6. 获取协议及版本：  HTTP/1.1
        * String getProtocol()

    7. 获取客户机的IP地址： IPV6地址 0:0:0:0:0:0:0:1
        * String getRemoteAddr()
 */
@WebServlet("/req_line")
public class RequestLine extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 获取请求方式 ：GET
        String method = request.getMethod();
        System.out.println(method);

        // 2. (重要)获取虚拟目录：/_01_Servlet
        String contextPath = request.getContextPath();
        System.out.println(contextPath);

        // 3. 获取Servlet路径: /req_line
        String servletPath = request.getServletPath();
        System.out.println(servletPath);

        // 4. 获取get方式请求参数：name=zhangsan&pwd=123
        String queryString = request.getQueryString();
        System.out.println(queryString);

        // 5. (重要)获取请求URI：/_01_Servlet/req_line
        String uri = request.getRequestURI();
        StringBuffer url = request.getRequestURL();
        System.out.println(uri);
        System.out.println(url);

        // 6. 获取协议及版本：HTTP/1.1
        String protocol = request.getProtocol();
        System.out.println(protocol);

        // 7. 获取客户机的IP地址： 0:0:0:0:0:0:0:1
        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);
    }
}
