package com.ashen.usercenter.mq;

import com.ashen.usercenter.domain.dto.mq.UserAddBonusMsgDto;
import com.ashen.usercenter.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Rocket MQ 消费者类
 * 实现 RocketMQListener 接口标识这是一个消费者类，泛型指定为消息实体
 * 注解 @RocketMQMessageListener 属性指定消费的一些信息，topic、group等
 * 重写接口的 onMessage 消费方法
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "add-bonus", consumerGroup = "consumer-group")
public class AddBonusMQListener implements RocketMQListener<UserAddBonusMsgDto> {
    
    @Resource
    private UserService userService;

    @Override
    public void onMessage(UserAddBonusMsgDto userAddBonusMsgDto) {
        userService.addBonus(userAddBonusMsgDto);
    }
}
