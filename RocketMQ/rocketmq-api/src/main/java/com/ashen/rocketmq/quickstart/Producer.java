package com.ashen.rocketmq.quickstart;

import com.ashen.rocketmq.constant.Const;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

public class Producer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        // 创建producer对象
        DefaultMQProducer producer = new DefaultMQProducer("test_quick_producer_name");
        // 设置 NamesrvAddr
        producer.setNamesrvAddr(Const.NAMESRV_ADDR_SINGLE);
        
        // 可以设置诸多生产者参数
        // producer.setCreateTopicKey("test_topic_1");     // 创建topic
        // producer.setMaxMessageSize(1024 * 1024 * 2);    // 2M
        // producer.setDefaultTopicQueueNums(4);           // 队列数量
        
        // 开启生产者
        producer.start();

        for (int i = 0; i < 5; i++) {
            // 创建消息
            Message message = new Message("test_quick_topic",
                    "TagA",
                    "key_" + i,
                    ("Hello RocketMQ " + i).getBytes());
            message.setDelayTimeLevel(1);   // 设置延迟级别
            
            // 同步发送消息
            // SendResult result = producer.send(message);
            // System.err.println("消息发出：" + result);
            
            // 异步发送消息
            // producer.send(message, new SendCallback() {
            //     @Override
            //     public void onSuccess(SendResult sendResult) {
            //         System.err.printf("msgId: %s, sendStatus: %s\n", 
            //                 sendResult.getMsgId(), sendResult.getSendStatus());
            //     }
            //
            //     @Override
            //     public void onException(Throwable e) {
            //         System.err.println("------发送失败------");
            //     }
            // });

            // 自定义投递
            SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Integer queueNumber = (Integer) arg;
                    return mqs.get(queueNumber);
                }
            }, 2);
            System.err.println(sendResult.getMessageQueue().getQueueId());  // 2
        }
        
        // 关闭生产者
        // producer.shutdown();
        
    }
}
