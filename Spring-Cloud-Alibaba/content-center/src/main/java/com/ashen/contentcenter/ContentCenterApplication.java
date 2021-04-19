package com.ashen.contentcenter;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.ashen.contentcenter.rocketmq.MySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Collections;

@MapperScan("com.ashen.contentcenter.dao")
@SpringBootApplication
@EnableFeignClients
@EnableBinding({Source.class, MySource.class})
public class ContentCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentCenterApplication.class, args);
    }
    
    @Bean
    @LoadBalanced  //负载均衡注解
    @SentinelRestTemplate  // Sentinel整合RestTemplate
    public RestTemplate restTemplate() {
        RestTemplate template = new RestTemplate();
        // 配置拦截器链
        template.setInterceptors(Collections.singletonList(
                new RelayTokenClientHttpRequestInterceptor()
        ));
        return template;
    }
}
