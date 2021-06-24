package com.ashen.payb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.ashen.common.config", "com.ashen.payb"})
@MapperScan(basePackages = "com.ashen.payb.dao")
public class PayBApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayBApplication.class, args);
    }
    
}
