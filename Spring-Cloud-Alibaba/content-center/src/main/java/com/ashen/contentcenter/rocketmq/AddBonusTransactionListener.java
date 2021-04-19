package com.ashen.contentcenter.rocketmq;

import com.alibaba.fastjson.JSON;
import com.ashen.contentcenter.domain.entity.messaging.RocketmqTransactionLog;
import com.ashen.contentcenter.dao.messaging.RocketmqTransactionLogMapper;
import com.ashen.contentcenter.domain.dto.cotent.ShareAuditDTO;
import com.ashen.contentcenter.service.content.ShareService;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import javax.annotation.Resource;

/**
 * Rocket MQ 本地事务监听器
 * 封装了本地事务执行的方法，和事务回查的方法
 */
@RocketMQTransactionListener(txProducerGroup = "tx-add-bonus-group")
public class AddBonusTransactionListener implements RocketMQLocalTransactionListener {

    @Resource
    private ShareService shareService;
    @Resource
    private RocketmqTransactionLogMapper rocketmqTransactionLogMapper;

    /**
     * 执行本地事务的方法
     * RocketMQ事务是先发送半消息，再执行本地事务，所以本方法中才真正执行审核操作
     *
     * @param msg MQ消息对象
     * @param arg 发送MQ消息的额外参数
     * @return 返回RocketMQ事务三态之一，COMMIT/ROLLBACK/UNKNOWN
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        // 取出消息header中的信息
        MessageHeaders headers = msg.getHeaders();
        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        Integer shareId = Integer.valueOf((String) headers.get("share_id"));
        String dtoString = (String) headers.get("dto");
        ShareAuditDTO auditDTO = JSON.parseObject(dtoString, ShareAuditDTO.class);

        // 真正执行审核操作，并发送事务后续操作 commit/rollback
        try {
            shareService.auditByIdWithRocketMQLog(shareId, auditDTO, transactionId);
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * 检查本地事务的方法
     * 检查事务日志表中是否有记录，来判断本地事务是否成功
     *
     * @param msg MQ消息对象
     * @return 返回RocketMQ事务三态之一，COMMIT/ROLLBACK/UNKNOWN
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        // 从消息总获取事务id
        MessageHeaders headers = msg.getHeaders();
        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        // 查询事务日志记录
        RocketmqTransactionLog transactionLog = rocketmqTransactionLogMapper.selectOne(
                RocketmqTransactionLog.builder().transactionId(transactionId).build()
        );
        // 根据本地事务状态发送 commit/rollback 消息
        if (transactionLog != null) {
            return RocketMQLocalTransactionState.COMMIT;
        }
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}
