package com.ashen.paya.service.producer;

import com.ashen.common.utils.FastJsonConvertUtil;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class CallbackService {

    public static final String CALLBACK_PAY_TOPIC = "callback_pay_topic";
    public static final String CALLBACK_PAY_TAGS = "callback_pay";

    @Autowired
    private SyncProducer syncProducer;

    /**
     * 回调订单系统，告知支付成功
     */
    public void sendOKMessage(String orderId, String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("orderId", orderId);
        params.put("status", "2");    // ok

        String keys = UUID.randomUUID().toString().replaceAll("-", "") + "$" + System.currentTimeMillis();
        Message message = new Message(CALLBACK_PAY_TOPIC, CALLBACK_PAY_TAGS, keys,
                FastJsonConvertUtil.convertObjectToJSON(params).getBytes());

        SendResult ret = syncProducer.sendMessage(message);
    }

}
