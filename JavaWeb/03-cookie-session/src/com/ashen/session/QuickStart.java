package com.ashen.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
Session：
	1. 概念：服务器端会话技术，在一次会话的多次请求间共享数据，将数据保存在服务器端的对象中。HttpSession
	2. 快速入门：
		1. 获取HttpSession对象：
			HttpSession session = request.getSession();
		2. 使用HttpSession对象：
			Object getAttribute(String name)
			void setAttribute(String name, Object value)
			void removeAttribute(String name)

	3. 原理
		* Session的实现是依赖于Cookie的。
		* 通过Cookie中的JSESSIONID，服务器可以确定本次会话用的是哪个Session。
		  关闭浏览器再次访问会找不到session的会话id而不是session被销毁了。
 */
@WebServlet("/session_attr")
public class QuickStart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 获取Session
        HttpSession session = request.getSession();
        System.out.println(session);

        // Session共享数据
        session.setAttribute("username", "zhangsan");
        Object username = session.getAttribute("username");
        session.removeAttribute("username");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
