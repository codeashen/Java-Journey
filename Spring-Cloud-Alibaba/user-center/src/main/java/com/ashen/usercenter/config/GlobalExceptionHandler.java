package com.ashen.usercenter.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public String exception(Throwable e) {
        return e.getMessage();
    }
}
