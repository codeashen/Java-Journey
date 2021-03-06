package com.ashen.rocketmq.consumer.pull;

import com.ashen.rocketmq.constant.Const;
import org.apache.rocketmq.client.consumer.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

public class PullScheduleService {
    public static void main(String[] args) throws MQClientException {

        final MQPullConsumerScheduleService scheduleService = new MQPullConsumerScheduleService("test_pull_consumer_name");
        scheduleService.getDefaultMQPullConsumer().setNamesrvAddr(Const.M2_S2_ASYNC);
        scheduleService.setMessageModel(MessageModel.CLUSTERING);

        scheduleService.registerPullTaskCallback("test_pull_topic", new PullTaskCallback() {

            @Override
            public void doPullTask(MessageQueue mq, PullTaskContext context) {
                // 从context中拿到consumer
                MQPullConsumer consumer = context.getPullConsumer();
                System.err.println("-------------- queueId: " + mq.getQueueId() + "-------------");
                try {
                    // 获取从哪里拉取
                    long offset = consumer.fetchConsumeOffset(mq, false);
                    if (offset < 0)
                        offset = 0;

                    PullResult pullResult = consumer.pull(mq, "*", offset, 32);
                    System.out.printf("%s%n", offset + "\t" + mq + "\t" + pullResult);
                    switch (pullResult.getPullStatus()) {
                        case FOUND:
                            List<MessageExt> list = pullResult.getMsgFoundList();
                            for (MessageExt msg : list) {
                                //消费数据...
                                System.out.println(new String(msg.getBody()));
                            }
                            break;
                        case NO_MATCHED_MSG:
                            break;
                        case NO_NEW_MSG:
                        case OFFSET_ILLEGAL:
                            break;
                        default:
                            break;
                    }
                    // 更新offset
                    consumer.updateConsumeOffset(mq, pullResult.getNextBeginOffset());
                    // 设置再过3000ms后重新拉取
                    context.setPullNextDelayTimeMillis(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        scheduleService.start();
    }
}
