package com.ashen.web.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 演示获取字符流，输出数据
 */
@WebServlet("/writer")
public class CharacterStream extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        // 通过response获取的字符流的默认编码是ISO-8859-1，获取流之前要修改流编码
        response.setCharacterEncoding("utf-8");

        // 告诉浏览器响应编码，让浏览器正确解码
        response.setHeader("content-type", "text/html;charset=utf-8");
        */

        // 设置响应格式和编码，一定要在获取流之前（简单方法，无需以上的内容了）
        response.setContentType("text/html;charset=utf-8");

        // 获取字符流
        PrintWriter writer = response.getWriter();
        writer.write("你好，字符流");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
