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

/*
cookie的细节
    1. 一次可不可以发送多个cookie?
        * 可以，创建多个Cookie对象，使用response调用多次addCookie方法发送cookie即可。
        * Cookie名称相同的话，后发送的Cookie值会覆盖前面的同名Cookie
    2. cookie在浏览器中保存多长时间？
        1. 默认情况下，当浏览器关闭后，Cookie数据被销毁
        2. 持久化存储：
            * setMaxAge(int seconds)
                1. 正数：将Cookie数据写到硬盘的文件中。持久化存储。并指定cookie存活时间，时间到后，cookie文件自动失效
                2. 负数：默认值
                3. 零  ：删除cookie信息
    3. cookie能不能存中文？
        * 在tomcat 8 之前 cookie中不能直接存储中文数据。
            * 需要将中文数据转码---一般采用URL编码(%E3)
        * 在tomcat 8 之后，cookie支持中文数据。特殊字符还是不支持，建议使用URL编码存储，URL解码解析
    4. cookie共享问题？
        1. 假设在一个tomcat服务器中，部署了多个web项目，那么在这些web项目中cookie能不能共享？
            * 默认情况下cookie不能共享

            * setPath(String path):设置cookie的获取范围。默认情况下，设置当前的虚拟目录
                * 如果要共享，则可以将path设置为"/"

        2. 不同的tomcat服务器间cookie共享问题？
            * setDomain(String path):如果设置一级域名相同，那么多个服务器之间cookie可以共享
                * setDomain(".baidu.com"),那么tieba.baidu.com和news.baidu.com中cookie可以共享


Cookie的特点和作用
    1. cookie存储数据在客户端浏览器
    2. 浏览器对于单个cookie 的大小有限制(4kb) 以及 对同一个域名下的总cookie数量也有限制(20个)

    * 作用：
        1. cookie一般用于存出少量的不太敏感的数据
        2. 在不登录的情况下，完成服务器对客户端的身份识别
 */
@WebServlet("/cookieDetail")
public class CookieDetail extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie c1 = new Cookie("name", "zhangsan");
        //Cookie c2 = new Cookie("msg", "hello world"); //有空格，需要数据转码

        // URL编码
        String msg = URLEncoder.encode("hello world", "utf-8");
        Cookie c2 = new Cookie("msg", msg);

        // 模拟取数据，需要URL解码
        String value = c2.getValue();
        value = URLDecoder.decode(value, "utf-8");

        // 设置Cookie的存活时间（单位：s）
        c2.setMaxAge(3 * 60);  // c2持久化存储，存活时间3min
        // c2.setMaxAge(-1); // 负数=默认情况，关闭浏览器销毁Cookie，c2
        // c2.setMaxAge(0);  // 0，清除Cookie，c2

        // Cookie共享问题
        c1.setPath("/");  // 设置Cookie，c1在当前服务器所有web项目范围内共享
        // 默认是只在当前虚拟目录，即当前web项目内共享

        // 一次发送多个Cookie
        response.addCookie(c1);
        response.addCookie(c2);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
