package com.ashen.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
Cookie：
	1. 概念：客户端会话技术，将数据保存到客户端

	2. 快速入门：
		* 使用步骤：
			1. 创建Cookie对象，绑定数据
				* new Cookie(String name, String value)
			2. 发送Cookie对象
				* response.addCookie(Cookie cookie)
			3. 获取Cookie
				* Cookie[]  request.getCookies()
		    4. Cookie中取数据
		        * cookie.getName()
		        * cookie.getValue()
		        * cookie.setValue(String newValue)

	3. 实现原理
		* 服务器发送Cookie：基于响应头set-cookie
		* 服务器接收Cookie：基于请求头cookie实现
 */
@WebServlet("/send")
public class SendCookie extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.创建Cookie对象
        Cookie cookie = new Cookie("msg", "hello");
        // 2.发送Cookie
        response.addCookie(cookie);

        /*
        其实设置了响应头 set-setting : msg=hello
         */
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
