package com.ashen.rocketmq.transaction;

import com.ashen.rocketmq.constant.Const;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;

/**
 * RocketMQ事务消息生产者示例
 */
public class TransactionProducer {

    public static final String TX_PRODUCER_GROUP = "test_tx_producer_group";
    public static final String TX_TOPIC = "test_tx_topic";
    
    public static void main(String[] args) throws MQClientException {
        // Producer使用的线程池
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                5,
                100,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(2000),
                r -> new Thread(r, "test_tx_producer_group-check-thread"),
                new ThreadPoolExecutor.AbortPolicy()
        );
        
        // Producer的Listener对象，做两件事情：1-异步执行本地事务 2-供MQ做事务回查
        TransactionListener transactionListener = new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                System.err.println("----------- 执行本地事务 -----------");
                System.err.println("callArg: " + arg);
                // tx.begin
                // 数据库落库操作
                // tx.commit
                // return LocalTransactionState.COMMIT_MESSAGE;
                return LocalTransactionState.UNKNOW;    // 如果返回unknow，MQ会执行事务回查
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                System.err.println("----------- 本地事务回查 -----------");
                // 回查逻辑
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        };

        // 创建事务Producer并启动
        TransactionMQProducer producer = new TransactionMQProducer(TX_PRODUCER_GROUP);
        producer.setNamesrvAddr(Const.M2_S2_ASYNC);
        producer.setExecutorService(executorService);
        producer.setTransactionListener(transactionListener);
        producer.start();
        
        // 发送事务消息
        Message message = new Message(TX_TOPIC, "TagA", "TX-Key", 
                "Hello, Transaction Message".getBytes(StandardCharsets.UTF_8));
        producer.sendMessageInTransaction(message, "我是回调参数，本地事务执行和事务回查，都能拿到我");

        // producer.shutdown();
    }
}
