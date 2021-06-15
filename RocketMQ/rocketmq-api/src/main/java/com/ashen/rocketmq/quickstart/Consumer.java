package com.ashen.rocketmq.quickstart;

import com.ashen.rocketmq.constant.Const;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.Objects;

public class Consumer {

    public static void main(String[] args) throws MQClientException {
        // 创建消费者对象
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test_quick_consumer_name");
        // 设置 NameServer 地址
        consumer.setNamesrvAddr(Const.NAMESRV_ADDR_SINGLE);
        // 设置消费位点
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        // 指定订阅topic和订阅tag的表达式
        consumer.subscribe("test_quick_topic", "*");
        
        // 监听消息，指定消费行为
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                MessageExt me = msgs.get(0);
                try {
                    String topic = me.getTopic();
                    String tags = me.getTags();
                    String keys = me.getKeys();
                    if (Objects.equals(keys, "key_1")) {
                        System.err.println("消息消费失败...");
                        int a = 1 / 0;
                    }
                    
                    String body = new String(me.getBody());
                    System.err.printf("topic: %s, tags: %s, keys: %s, body: %s\n", 
                            topic, tags, keys, body);
                } catch (Exception e) {
                    e.printStackTrace();
                    // 获取重试次数
                    int reconsumeTimes = me.getReconsumeTimes();
                    System.err.println("reconsumeTimes: " + reconsumeTimes);
                    if (reconsumeTimes == 3) {
                        // 如果已经重试3次了，记录日志，做补偿处理...
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                    // 返回稍后重试
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }

                // 返回消费成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        // 开启消费者
        consumer.start();
        System.err.println("Consumer start...");
    }
}
