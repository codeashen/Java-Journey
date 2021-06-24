package com.ashen.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.ashen.common.config", "com.ashen.store"})
@MapperScan(basePackages = "com.ashen.store.dao")
public class StoreApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }
}
