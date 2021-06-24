package com.ashen.rocketmq.orderly;

import com.ashen.rocketmq.constant.Const;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderlyProducer {

    public static final String ORDERLY_PRODUCER_GROUP = "test_orderly_producer_name";
    public static final String ORDERLY_TOPIC = "test_orderly_producer_name";

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer(ORDERLY_PRODUCER_GROUP);
        producer.setNamesrvAddr(Const.M2_S2_ASYNC);
        producer.start();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i < 10; i++) {
            String tag = i % 2 == 0 ? "A" : "B";
            String key = "key-" + i;
            byte[] body = sdf.format(new Date()).getBytes(StandardCharsets.UTF_8);
            Message message = new Message(ORDERLY_TOPIC, tag, key, body);
            
            // 偶数投递到0队列，奇数投递到1队列
            SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    int index = (Integer) arg % 2;
                    return mqs.get(index);
                }
            }, i);

            System.out.println(sendResult);
        }

        producer.shutdown();
    }
}
