package com.ashen.rocketmq.consumer.model;

import com.ashen.rocketmq.constant.Const;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Consumer2 {

    public Consumer2() {
        try {
            String group_name = "test_model_consumer_name2";
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(group_name);
            consumer.setNamesrvAddr(Const.NAMESRV_ADDR_MASTER_SLAVE);
            consumer.subscribe("test_model_topic", "TagB");
            consumer.setMessageModel(MessageModel.CLUSTERING);
            // consumer.setMessageModel(MessageModel.BROADCASTING);
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    return null;
                }
            });
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class Listener implements MessageListenerConcurrently {
        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
            try {
                for (MessageExt msg : msgs) {
                    String topic = msg.getTopic();
                    String msgBody = new String(msg.getBody(), StandardCharsets.UTF_8);
                    String tags = msg.getTags();
                    //if(tags.equals("TagA")) {
                    System.out.printf("收到消息：topic: %s, tags: %s, body: %s\n", topic, tags, msgBody);
                    //}				
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
    }

    public static void main(String[] args) {
        Consumer2 c1 = new Consumer2();
        System.out.println("c2 start..");
    }
}
