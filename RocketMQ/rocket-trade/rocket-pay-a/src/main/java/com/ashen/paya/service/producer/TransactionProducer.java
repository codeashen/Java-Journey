package com.ashen.paya.service.producer;

import com.ashen.common.constant.MqConst;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * 事务消息生产者
 */
@Component
public class TransactionProducer implements InitializingBean {

    private static final String PRODUCER_GROUP_NAME = "tx_pay_producer_group_name";

    // 事务消息生产者
    private TransactionMQProducer producer;
    // 线程池
    private ExecutorService executorService;

    @Autowired
    private TransactionListenerImpl transactionListenerImpl;

    @Override
    public void afterPropertiesSet() throws Exception {
        producer.setTransactionListener(transactionListenerImpl);
        start();
    }

    private TransactionProducer() {
        producer = new TransactionMQProducer(PRODUCER_GROUP_NAME);
        executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, PRODUCER_GROUP_NAME + "-check-thread");
            }
        });
        producer.setExecutorService(executorService);
        producer.setNamesrvAddr(MqConst.NAMESERVER);
    }


    private void start() {
        try {
            this.producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        this.producer.shutdown();
    }

    public TransactionSendResult sendMessage(Message message, Object argument) {
        TransactionSendResult sendResult = null;
        try {
            sendResult = this.producer.sendMessageInTransaction(message, argument);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult;
    }

}
