package com.ashen.web.response;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 演示获取字节流，输出数据
 */
@WebServlet("/out")
public class ByteStream extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应编码
        response.setContentType("text/html;charset=utf-8");

        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write("你好，字节流".getBytes("utf-8"));

        /*
        复习：String中方法，getBytes()
            byte[] getBytes()  :  返回系统默认编码的字节数组
            byte[] getBytes(String charsetName)  :  返回指定编码的字符数组
         */

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
