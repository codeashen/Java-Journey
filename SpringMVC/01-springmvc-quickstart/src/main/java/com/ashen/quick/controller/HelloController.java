package com.ashen.quick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller入门类
 * RequestMapping注解属性：
 *      value/path -- 都是用来指定请求路径
 *      method ------ 指定请求方式
 *      params ------ 用于指定限制请求参数的条件。它支持简单的表达式。要求请求参数的key和value必须和配置的一模一样。
 *                    例如：
 *                    params = {"accountName"}，表示请求参数必须有accountName
 *                    params = {"moeny!100"}，表示请求参数中money 不能是100。
 *      headers ----- 用于指定限制请求消息头的条件。
 */
@Controller
@RequestMapping("/quick")
public class HelloController {

    /**
     * 入门案例
     * @return 返回字符串经过视图解析器
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        System.out.println("Hello SpringMVC");
        return "success";
    }
}
