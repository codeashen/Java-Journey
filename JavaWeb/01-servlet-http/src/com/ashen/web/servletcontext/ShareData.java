package com.ashen.web.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
ServletContext功能：
    1. 获取MIME类型：

    2. 域对象：共享数据
        1. setAttribute(String name,Object value)
        2. getAttribute(String name)
        3. removeAttribute(String name)

        * ServletContext对象范围：所有用户所有请求的数据
        ServletContext域对象，服务器启动创建，服务器关闭才销毁

    3. 获取文件的真实(服务器)路径
 */
@WebServlet("/attr")
public class ShareData extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 获取ServletContext对象
        ServletContext servletContext = this.getServletContext();

        servletContext.setAttribute("count", 1);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
