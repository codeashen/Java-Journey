package com.ashen.contentcenter;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

@SpringBootTest
@Slf4j
class ContentCenterApplicationTests {
    
    // 服务发现组件
    @Autowired
    private DiscoveryClient discoveryClient;

    @Test
    void contextLoads() {
    }

    /**
     * 测试服务发现
     */
    @Test
    void testGetService() {
        List<ServiceInstance> instances = discoveryClient.getInstances("user-center");
        for (ServiceInstance instance : instances) {
            System.err.println(instance.getServiceId() + " " + instance.getHost() + ":" + instance.getPort());
        }
    }

}
