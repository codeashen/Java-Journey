package com.ashen.contentcenter.controller.content;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ashen.contentcenter.domain.dto.user.UserDTO;
import com.ashen.contentcenter.rocketmq.MySource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Source source;
    @Autowired
    private MySource mySource;

    /**
     * 测试sentinel热点参数限流
     */
    @GetMapping("/sentinel/param")
    @SentinelResource(value = "hot", blockHandler = "sentinelParamControlBlockHandler")
    public String sentinelParamControl(@RequestParam(required = false) String a,
                                       @RequestParam(required = false) String b) {
        return a + " " + b;
    }

    /**
     * hot资源的限流处理方法
     * @return
     */
    public String sentinelParamControlBlockHandler(String a, String b, BlockException e) {
        log.warn("hot资源被限流，a={}，b={}，e={}", a, b, e.getMessage());
        return "hot资源被限流";
    }

    /**
     * 测试sentinel整合RestTemplate
     * @param userId
     * @return
     */
    @GetMapping("/sentinel/rest_template/{userId}")
    public UserDTO sentinelRestTemplate(@PathVariable Integer userId) {
        return restTemplate.getForObject("http://user-center/users/{userId}", UserDTO.class, userId);
    }

    /**
     * 测试Stream发送MQ消息
     * @return
     */
    @GetMapping("/stream")
    public String testStream() {
        Message<String> message = MessageBuilder.withPayload("消息体").build();
        source.output().send(message);
        return "success";
    }

    /**
     * 测试自定义Stream接口发送MQ消息
     * @return
     */
    @GetMapping("/stream2")
    public String testStream2() {
        Message<String> message = MessageBuilder.withPayload("消息体").build();
        mySource.output().send(message);
        return "success";
    }
}
