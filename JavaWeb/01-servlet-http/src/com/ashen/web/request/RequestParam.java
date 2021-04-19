package com.ashen.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

/*
获取请求参数通用方式：不论get还是post请求方式都可以使用下列方法来获取请求参数
    1. String getParameter(String name):根据参数名称获取参数值    username=zs&password=123
    2. String[] getParameterValues(String name):根据参数名称获取参数值的数组  hobby=study&hobby=game
    3. Enumeration<String> getParameterNames():获取所有请求参数的名称
    4. Map<String,String[]> getParameterMap():获取所有参数的map集合

    * 中文乱码问题：
        * get方式：tomcat 8 已经将get方式乱码问题解决了
        * post方式：会乱码
            * 解决：在获取参数前，设置request的编码request.setCharacterEncoding("utf-8");
 */
@WebServlet("/req_parameter")
public class RequestParam extends HttpServlet {
    // 使用register.html演示方法
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.根据参数名称获取参数值
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        System.out.println("---------------------");

        // 2.根据参数名称获取参数值的数组
        String[] hobbies = request.getParameterValues("hobby");
        for (String hobby : hobbies) {
            System.out.println(hobby);
        }

        System.out.println("---------------------");

        // 3.获取所有请求参数的名称
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String param_name = names.nextElement();
            System.out.println(param_name);
        }

        System.out.println("---------------------");

        // 4.获取所有请求参数的Map集合
        Map<String, String[]> parameterMap = request.getParameterMap();

        // 4.1 遍历方法一，keySet()
        Set<String> keySet = parameterMap.keySet();
        for (String key : keySet) {
            String[] values = parameterMap.get(key);

            System.out.println(key + ":");
            for (String value : values) {
                System.out.println(value);
            }
        }

        // 4.2 遍历方法二，entrySet()
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            String key = entry.getKey();
            String[] values = entry.getValue();

            System.out.println(key + ":");
            for (String value : values) {
                System.out.println(value);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
