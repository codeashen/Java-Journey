package com.ashen.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/*
Session细节：
    1. 当客户端关闭后，服务器不关闭，两次获取session是否为同一个？
        * 默认情况下。不是。
        * 如果需要相同，则可以创建Cookie,键为JSESSIONID，设置最大存活时间，让cookie持久化保存。
             Cookie c = new Cookie("JSESSIONID",session.getId());
             c.setMaxAge(60*60);
             response.addCookie(c);

    2. 客户端不关闭，服务器关闭后，两次获取的session是同一个吗？
        * 不是同一个，但是要确保数据不丢失。tomcat自动完成以下工作（IDEA部署不行）
            * session的钝化：
                * 在服务器正常关闭之前，将session对象序列化到硬盘上。（work目录）
            * session的活化：
                * 在服务器启动后，将session文件转化为内存中的session对象即可。

    3. session什么时候被销毁？
        1. 服务器关闭
        2. session对象调用invalidate() 。
        3. session默认失效时间 30分钟
            选择性配置修改，在web.xml中配置
            <session-config>
                <session-timeout>30</session-timeout>
            </session-config>

Session的特点
     1. session用于存储一次会话的多次请求的数据，存在服务器端
     2. session可以存储任意类型，任意大小的数据

    * session与Cookie的区别：
        1. session存储数据在服务器端，Cookie在客户端
        2. session没有数据大小限制，Cookie有
        3. session数据安全，Cookie相对于不安全
 */
@WebServlet("/session_detail")
public class SessionDetail extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        System.out.println();

        // 要浏览器关闭后再次访问获取相同的session，就要设置JSESSIONID的cookie持久化
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setMaxAge(30 * 60); //30分钟，对应session的默认失效时间
        response.addCookie(cookie);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
