package com.ashen.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/receive")
public class ReceiveCooike extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接收Cookie数组
        Cookie[] cookies = request.getCookies();

        // 2.遍历Cookie数组（非空判断，防止空指针异常）
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                // 3.Cookie中取数据
                String name = cookie.getName();
                String value = cookie.getValue();
                System.out.println(name + " : " + value);
            }
        }

        /*
        其实获取了请求头  cookie : msg=hello
         */
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
