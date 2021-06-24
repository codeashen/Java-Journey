package com.ashen.rocketmq.orderly;

import com.ashen.rocketmq.constant.Const;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class OrderlyConsumer {

    public static final String ORDERLY_CONSUMER_GROUP = "test_orderly_consumer_name";
    public static final String ORDERLY_TOPIC = "test_orderly_producer_name";
    
    public OrderlyConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(ORDERLY_CONSUMER_GROUP);
        consumer.setNamesrvAddr(Const.M2_S2_ASYNC);
        // 设置第一次启动从队列头部开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe(ORDERLY_TOPIC, "*");
        consumer.registerMessageListener(new Listener());
        consumer.start();
        System.err.println("consumer started...");
    }

    // 消费者监听对象，实现的是 MessageListenerOrderly 接口
    class Listener implements MessageListenerOrderly {

        private Random random = new Random();

        @Override
        public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
            for (MessageExt msg : msgs) {
                System.out.println(msg + ", content: " + new String(msg.getBody()));
                try {
                    TimeUnit.SECONDS.sleep(random.nextInt(4) + 1);
                } catch (Exception e) {
                    e.printStackTrace();
                    // return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;  // 暂停当前队列片刻
                }
            }

            return ConsumeOrderlyStatus.SUCCESS;  // 消费成功
        }
    }

    public static void main(String[] args) throws MQClientException {
        new OrderlyConsumer();
    }
    
}
