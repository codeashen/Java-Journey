package com.ashen.param.controller;

import com.ashen.param.domain.Bank;
import com.ashen.param.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class ParamController {

    /**
     * 一、基本数据类型参数绑定
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public String login(String username, String password) {
        System.out.println("username = " + username + ",  password = " + password);
        return "success";
    }

    /**
     * 二、参数绑定到对象（对象中含对象）
     * @param person Person类中有IdCard属性
     * @return
     */
    @RequestMapping("/info")
    public String register(Person person) {
        System.out.println(person);
        return "success";
    }

    /**
     * 三、参数绑定到对象（对象中有集合）
     * @param bank Bank类中有List和Map
     * @return
     */
    @RequestMapping("/bank")
    public String bank(Bank bank) {
        System.out.println(bank);
        return "success";
    }

    /**
     * 四、自定义类型转换器（转换yyyy-MM-dd格式日期）
     * @param person
     * @return
     */
    @RequestMapping("/convert")
    public String convert(Person person) {
        System.out.println(person);
        return "success";
    }

    /**
     * 五、绑定原生Servlet的API
     * request可以获取session，session可以获取ServletContext
     * @param request   request对象
     * @param response  response对象
     * @return
     */
    @RequestMapping("servlet")
    public String servlet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ServletContext servletContext = session.getServletContext();
        System.out.println("四大域对象：");
        System.out.println(request);
        System.out.println(response);
        System.out.println(session);
        System.out.println(servletContext);
        return "success";
    }
}
