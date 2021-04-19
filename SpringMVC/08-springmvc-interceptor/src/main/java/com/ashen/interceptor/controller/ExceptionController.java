package com.ashen.interceptor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/interceptor")
public class ExceptionController {

    @RequestMapping("/test")
    public String test() {
        System.out.println("controller方法执行了...");
        return "success";
    }
}
