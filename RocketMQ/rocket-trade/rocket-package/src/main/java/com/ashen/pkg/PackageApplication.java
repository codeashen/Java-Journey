package com.ashen.pkg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.ashen.common.config", "com.ashen.pkg"})
@MapperScan(basePackages = "com.ashen.pkg.dao")
public class PackageApplication {

    public static void main(String[] args) {
        SpringApplication.run(PackageApplication.class, args);
    }
}

