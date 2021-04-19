package com.ashen.code.servlet;

import com.ashen.code.dao.UserDao;
import com.ashen.code.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 用户登录Servlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码
        request.setCharacterEncoding("utf-8");

        // 获取所有请求参数的Map集合
        Map<String, String[]> parameterMap = request.getParameterMap();
        String code_input = request.getParameter("checkCode");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 获取session中的checkCode
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("checkCode");
        // 获取后删除session中验证码，防止浏览器后退动作反复使用同一验证码
        session.removeAttribute("checkCode");

        // 校验验证码
        if (checkCode != null && checkCode.equalsIgnoreCase(code_input)) {
            // 验证码相同，开始校验用户名和密码
            // 封装User对象
            User loginUser = new User();
            try {
                BeanUtils.populate(loginUser, parameterMap);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            // 调用UserDao的查询方法
            UserDao dao = new UserDao();
            User user = dao.login(loginUser);

            // 判断登录结果
            if (user == null) {
                request.setAttribute("msg", "用户名或密码错误");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {
                // 登录成功，存数据 user信息到session，重定向到成功界面
                session.setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/success.jsp");
            }

        } else {
            // 验证码校验不通过
            request.setAttribute("msg", "验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
