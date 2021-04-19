package com.ashen.usercenter.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MyTestStreamConsumer {
    
    @StreamListener(MySink.MY_INPUT)
    public void receive(String messageBody) {
        log.info("通过自定义接口收到消息，messageBody={}", messageBody);
    }
}
