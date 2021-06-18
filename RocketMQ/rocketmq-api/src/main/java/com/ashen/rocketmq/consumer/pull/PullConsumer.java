package com.ashen.rocketmq.consumer.pull;

import com.ashen.rocketmq.constant.Const;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PullConsumer {
    // Map<key, value>  key为指定的队列，value为这个队列拉取数据的最后位置
    private static final Map<MessageQueue, Long> offsetTable = new HashMap<MessageQueue, Long>();

    public static void main(String[] args) throws MQClientException {
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("test_pull_consumer_name");
        consumer.setNamesrvAddr(Const.M2_S2_ASYNC);
        consumer.start();
        System.err.println("consumer start");

        // 从TopicTest这个主题去获取所有的队列（默认会有4个队列）
        Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues("test_pull_topic");
        // 遍历每一个队列，进行拉取数据
        for (MessageQueue mq : mqs) {
            System.err.println("Consume from the queue: " + mq);

            SINGLE_MQ:
            while (true) {
                try {
                    // 从queue中获取数据，指定消息位置，单次最多拉取32条记录
                    PullResult pullResult = consumer.pullBlockIfNotFound(mq, "TagA", getMessageQueueOffset(mq), 32);
                    System.out.println(pullResult);
                    // 记录消费进度
                    putMessageQueueOffset(mq, pullResult.getNextBeginOffset());
                    // 根据拉取结果的不同状态，处理消息
                    switch (pullResult.getPullStatus()) {
                        case FOUND:           // 找到消息，进行消费
                            List<MessageExt> list = pullResult.getMsgFoundList();
                            for (MessageExt msg : list) {
                                System.err.printf("收到消息：tags:%s, keys:%s, body:%s\n", 
                                        msg.getTags(), msg.getKeys(), new String(msg.getBody()));
                            }
                            break;
                        case NO_MATCHED_MSG:  // 过滤结果不匹配
                            System.out.println("有不匹配的结果");
                            break;
                        case NO_NEW_MSG:      // 没有新消息
                            System.out.println("没有新的数据啦");
                            break SINGLE_MQ;
                        case OFFSET_ILLEGAL:  // 非法偏移量，可能太大或太小
                            System.out.println("偏移量错误");
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        consumer.shutdown();
    }

    /**
     * 记录消费进度
     * @param mq     消息队列
     * @param offset 消费进度
     */
    private static void putMessageQueueOffset(MessageQueue mq, long offset) {
        offsetTable.put(mq, offset);
    }


    /**
     * 获取消费进度
     * @param mq 消息队列
     * @return 返回消息进度offset
     */
    private static long getMessageQueueOffset(MessageQueue mq) {
        Long offset = offsetTable.get(mq);
        if (offset != null)
            return offset;
        return 0;
    }

}
