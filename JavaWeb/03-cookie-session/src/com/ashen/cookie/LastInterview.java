package com.ashen.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
验证码案例：记住上一次访问时间
    1. 需求：
        1. 访问一个Servlet，如果是第一次访问，则提示：您好，欢迎您首次访问。
        2. 如果不是第一次访问，则提示：欢迎回来，您上次访问时间为:显示时间字符串

    2. 分析：
        1. 可以采用Cookie来完成
        2. 在服务器中的Servlet判断是否有一个名为lastTime的cookie
            1. 有：不是第一次访问
                1. 响应数据：欢迎回来，您上次访问时间为:2018年6月10日11:50:20
                2. 写回Cookie：lastTime=2018年6月10日11:50:01
            2. 没有：是第一次访问
                1. 响应数据：您好，欢迎您首次访问
                2. 写回Cookie：lastTime=2018年6月10日11:50:01

    3. 问题：
        1.Cookie不支持特殊字符，要采用URL编码后，发送给浏览器，对应的获取浏览器Cookie数据后，也要解码
            即放在Cookie中的数据是URL编码后的，使用数据时要解码
 */
@WebServlet("/lastTime")
public class LastInterview extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容编码
        response.setContentType("text/html;charset=utf-8");

        // 1.获取所有的Cookie
        Cookie[] cookies = request.getCookies();

        // 2.遍历cookies，判断是否存在名为lastTime的Cookie
        boolean flag = false; //是否存在lastTime
        // 开始遍历
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("lastTime".equals(name)) {
                    flag = true; // 获取到了lastTime，不是第一次访问
                    String value = cookie.getValue();
                    // 用之前，URL解码
                    value = URLDecoder.decode(value, "utf-8");
                    // 3.做出响应
                    response.getWriter().write("欢迎回来，您的上次访问时间是：" + value);

                    // 4.发送新的lastTime的Cookie
                    String str_date = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
                    // 存之前，URL编码
                    value = URLEncoder.encode(str_date, "utf-8");
                    cookie.setValue(value); //设置新的值
                    cookie.setMaxAge(30 * 24 * 3600); //设置Cookie存活时间，一个月
                    response.addCookie(cookie); //重新发送cookie

                    break;
                }
            }
        }

        // 没找到lastTime，创建Cookie，发送
        if (flag == false || cookies == null || cookies.length == 0) {
            // 做出响应
            response.getWriter().write("您好，欢迎首次访问");

            String str_date = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
            // 存之前，URL编码
            str_date = URLEncoder.encode(str_date, "utf-8");

            Cookie cookie = new Cookie("lastTime", str_date);
            cookie.setMaxAge(30 * 24 * 3600); //设置Cookie存活时间，一个月
            response.addCookie(cookie); //重新发送cookie
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
