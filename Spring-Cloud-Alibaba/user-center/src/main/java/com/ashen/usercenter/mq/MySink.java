package com.ashen.usercenter.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MySink {

    String MY_INPUT = "my-input";

    /**
     * Input注解标记这是一个 Input Binding
     * 注解value和yml配置bindings下的名称一致
     */
    @Input(MY_INPUT)
    SubscribableChannel input();
}

