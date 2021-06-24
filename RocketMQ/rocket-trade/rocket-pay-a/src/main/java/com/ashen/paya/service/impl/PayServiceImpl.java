package com.ashen.paya.service.impl;

import com.ashen.common.utils.FastJsonConvertUtil;
import com.ashen.paya.dao.CustomerAccountMapper;
import com.ashen.paya.entity.CustomerAccount;
import com.ashen.paya.service.PayService;
import com.ashen.paya.service.producer.CallbackService;
import com.ashen.paya.service.producer.TransactionProducer;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

@Service
public class PayServiceImpl implements PayService {

    public static final String TX_PAY_TOPIC = "tx_pay_topic";
    public static final String TX_PAY_TAGS = "pay";

    @Autowired
    private CustomerAccountMapper customerAccountMapper;
    @Autowired
    private TransactionProducer transactionProducer;
    @Autowired
    private CallbackService callbackService;

    @Override
    public String payment(String userId, String orderId, String accountId, double money) {
        String paymentRet = "";

        try {
            // 最开始有一步 token验证操作（重复提单问题）

            BigDecimal payMoney = new BigDecimal(money);

            //加锁开始（获取）
            CustomerAccount oldAccount = customerAccountMapper.selectByPrimaryKey(accountId);
            BigDecimal currentBalance = oldAccount.getCurrentBalance();
            int currentVersion = oldAccount.getVersion();
            BigDecimal newBalance = currentBalance.subtract(payMoney);
            //加锁结束（释放）

            if (newBalance.doubleValue() > 0) {
                // 1.组装消息
                //  1.执行本地事务
                String keys = UUID.randomUUID().toString().replaceAll("-", "") + "$" + System.currentTimeMillis();
                Map<String, Object> params = new HashMap<>();
                params.put("userId", userId);
                params.put("orderId", orderId);
                params.put("accountId", accountId);
                params.put("money", money);    // 100

                Message message = new Message(TX_PAY_TOPIC, TX_PAY_TAGS, keys, FastJsonConvertUtil.convertObjectToJSON(params).getBytes());
                // 可能需要用到的参数
                params.put("payMoney", payMoney);
                params.put("newBalance", newBalance);
                params.put("currentVersion", currentVersion);
                // 同步阻塞
                CountDownLatch countDownLatch = new CountDownLatch(1);  // 线程闩，在本地事务中countDown
                params.put("currentCountDown", countDownLatch);

                // 消息发送并且 本地的事务执行
                TransactionSendResult sendResult = transactionProducer.sendMessage(message, params);

                countDownLatch.await();

                if (sendResult.getSendStatus() == SendStatus.SEND_OK
                        && sendResult.getLocalTransactionState() == LocalTransactionState.COMMIT_MESSAGE) {
                    // 回调order通知支付成功消息
                    callbackService.sendOKMessage(orderId, userId);
                    paymentRet = "支付成功!";
                } else {
                    paymentRet = "支付失败!";
                }
            } else {
                paymentRet = "余额不足!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            paymentRet = "支付失败!";
        }

        return paymentRet;
    }
}
