package com.ashen.paya.service.producer;

import com.ashen.paya.dao.CustomerAccountMapper;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * 事务消息监听实现，提供本地事务执行方法和本地事务回查方法
 */
@Component
public class TransactionListenerImpl implements TransactionListener {
    @Autowired
    private CustomerAccountMapper customerAccountMapper;

    /**
     * 本地事务方法
     *
     * @param msg 事务消息
     * @param arg 发送事务消息时的附带参数
     * @return 返回本地事务状态 提交、回滚、未知
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        System.err.println("执行本地事务单元------------");
        CountDownLatch currentCountDown = null;
        try {
            Map<String, Object> params = (Map<String, Object>) arg;
            String userId = (String) params.get("userId");
            String accountId = (String) params.get("accountId");
            String orderId = (String) params.get("orderId");
            BigDecimal payMoney = (BigDecimal) params.get("payMoney");    // 当前的支付款
            BigDecimal newBalance = (BigDecimal) params.get("newBalance");    // 前置扣款成功的余额
            int currentVersion = (int) params.get("currentVersion");
            currentCountDown = (CountDownLatch) params.get("currentCountDown");

            // updateBalance 传递当前的支付款 数据库操作: 
            Date currentTime = new Date();
            int count = this.customerAccountMapper.updateBalance(accountId, newBalance, currentVersion, currentTime);
            if (count == 1) {
                currentCountDown.countDown();
                return LocalTransactionState.COMMIT_MESSAGE;
            } else {
                currentCountDown.countDown();
                return LocalTransactionState.ROLLBACK_MESSAGE;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (currentCountDown != null) {
                currentCountDown.countDown();
            }
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
    }

    /**
     * 事务回查方法
     *
     * @param msg 事务消息ext
     * @return 返回本地事务状态 提交、回滚、未知
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        return null;
    }
}
