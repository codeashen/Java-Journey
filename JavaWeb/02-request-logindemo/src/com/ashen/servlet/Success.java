package com.ashen.servlet;

import com.ashen.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功
 */
@WebServlet("/success")
public class Success extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 设置响应内容和编码
        response.setContentType("text/html;charset=utf-8");

        // 获取request域中属性
        String username = ((User) request.getAttribute("user")).getUsername();
        // 输出响应消息
        response.getWriter().write("欢迎你，" + username);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
