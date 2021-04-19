package com.ashen.exception.controller;

import com.ashen.exception.exception.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exception")
public class ExceptionController {

    @RequestMapping("/test")
    public String testException() throws SysException {
        System.out.println("==== 模拟异常 ====");
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SysException("系统查询方法出错啦...");
        }
        return "success";
    }
}
