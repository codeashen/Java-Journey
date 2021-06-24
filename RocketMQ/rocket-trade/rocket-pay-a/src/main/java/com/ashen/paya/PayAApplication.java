package com.ashen.paya;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.ashen.common.config", "com.ashen.paya"})
@MapperScan(basePackages = "com.ashen.paya.dao")
public class PayAApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayAApplication.class, args);
    }
    
}
