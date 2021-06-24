package com.ashen.paya.service.producer;

import com.ashen.common.constant.MqConst;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Component;

/**
 * 同步发送支付成功消息生产者
 */
@Component
public class SyncProducer {

    private static final String PRODUCER_GROUP_NAME = "callback_pay_producer_group_name";

    private DefaultMQProducer producer;

    private SyncProducer() {
        this.producer = new DefaultMQProducer(PRODUCER_GROUP_NAME);
        this.producer.setNamesrvAddr(MqConst.NAMESERVER);
        this.producer.setRetryTimesWhenSendFailed(3);
        start();
    }

    public void start() {
        try {
            this.producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public SendResult sendMessage(Message message) {
        SendResult sendResult = null;
        try {
            sendResult = this.producer.send(message);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            e.printStackTrace();
        }
        return sendResult;
    }

    public void shutdown() {
        this.producer.shutdown();
    }
}
