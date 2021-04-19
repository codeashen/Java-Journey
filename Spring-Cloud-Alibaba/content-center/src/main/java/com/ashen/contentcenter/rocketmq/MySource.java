package com.ashen.contentcenter.rocketmq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MySource {
    String MY_OUTPUT = "my-output";

    /**
     * Output注解标记这是一个 Output Binding
     * 注解value和yml配置bindings下的名称一致
     */
    @Output(MY_OUTPUT)
    MessageChannel output();
}
