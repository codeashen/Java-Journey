package com.ashen.servlet;

import com.ashen.dao.UserDao;
import com.ashen.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 用户登录Servlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.设置编码
        request.setCharacterEncoding("utf-8");

        /*
        // 2.获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 3.封装User对象
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);
        */

        /*
        以上为传统方法，现在使用Apache的BeanUtils工具类完成JavaBean的封装
        方法： BeanUtils.populate(Object bean, Map property)
         */
        // 2.获取所有请求参数的Map集合
        Map<String, String[]> parameterMap = request.getParameterMap();
        // 3.封装User对象
        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 4.调用UserDao的查询方法
        UserDao dao = new UserDao();
        User user = dao.login(loginUser);

        // 5.判断登录结果
        if (user == null) {
            request.getRequestDispatcher("/fail").forward(request, response);
        } else {
            // 登录成功，存数据 user
            request.setAttribute("user", user);
            request.getRequestDispatcher("/success").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
