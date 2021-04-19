package com.ashen.authority.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MVC全局自定义配置类，实现接口 WebMvcConfigurer
 */
@Configuration
public class ViewConfig extends WebMvcConfigurationSupport {

    /**
     * 无逻辑跳转配置
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/product/add").setViewName("product-add");
        registry.addViewController("/user/add").setViewName("user-add");
        registry.addViewController("/role/add").setViewName("role-add");
        registry.addViewController("/permission/add").setViewName("permission-add");
    }
}
