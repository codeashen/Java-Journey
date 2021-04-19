package com.ashen.annotation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 测试SpringMVC常用注解的Controller（一）
 */
@Controller
@RequestMapping("/anno")
public class AnnoController {

    /**
     * 注解一：RequestParam，用于解决前端参数名和方法参数名不一致的情况
     * @param username 方法参数名为username，前端传来参数名为name，使用这个注解完成参数绑定
     * @return
     */
    @RequestMapping("/testRequestParam")
    public String testRequestParam(@RequestParam("name") String username) {
        System.out.println("username=" + username);
        return "success";
    }

    /**
     * 注解二：RequestBody，用于获取整个请求体的内容，常用于获取请求体中的json，并可以实现自动封装到实体中
     * 注意，只有post请求才有请求体。
     * @param body 表单的变成 key1=value1&key2=value2 形式；json为字符串或封装到实体
     * @return
     */
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String body) {
        System.out.println("请求体内容：" + body);
        return "success";
    }

    /**
     * 注解三：PathVariable，用于请求路径上占位符的参数绑定
     * @param sid 绑定占位符{id}到方法参数
     * @return
     */
    @RequestMapping("/testPathVariable/{id}")
    public String testPathVariable(@PathVariable("id") String sid) {
        System.out.println("url上的参数：" + sid);
        return "success";
    }

    /**
     * 注解四：RequestHeader，获取指定请求头，绑定到方法参数
     * @param accept
     * @return
     */
    @RequestMapping("/testRequestHeader")
    public String testRequestHeader(@RequestHeader("Accept") String accept) {
        System.out.println("请求头中Accpet为：\n" + accept);
        return "success";
    }

    /**
     * 注解五：CookieValue，获取指定的Cookie值，绑定到方法参数上
     * @param cookieValue
     * @return
     */
    @RequestMapping("/testCookieValue")
    public String testCookieValue(@CookieValue("JSESSIONID") String cookieValue) {
        System.out.println("Cookie中的JSESSIONID = " + cookieValue);
        return "success";
    }
}
