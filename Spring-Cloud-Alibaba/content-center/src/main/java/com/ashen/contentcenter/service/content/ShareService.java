package com.ashen.contentcenter.service.content;

import com.alibaba.fastjson.JSON;
import com.ashen.contentcenter.domain.entity.content.Share;
import com.ashen.contentcenter.domain.entity.messaging.RocketmqTransactionLog;
import com.ashen.contentcenter.dao.content.ShareMapper;
import com.ashen.contentcenter.dao.messaging.RocketmqTransactionLogMapper;
import com.ashen.contentcenter.domain.dto.cotent.ShareAuditDTO;
import com.ashen.contentcenter.domain.dto.cotent.ShareDTO;
import com.ashen.contentcenter.domain.dto.mq.UserAddBonusMsgDto;
import com.ashen.contentcenter.domain.dto.user.UserDTO;
import com.ashen.contentcenter.domain.enums.AuditStatusEnum;
import com.ashen.contentcenter.feignclient.UserCenterFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ShareService {
    private final ShareMapper shareMapper;
    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;
    private final UserCenterFeignClient userCenterFeignClient;
    private final RocketMQTemplate rocketMQTemplate;
    private final RocketmqTransactionLogMapper rocketmqTransactionLogMapper;
    private final Source source;
    
    public ShareDTO findById(Integer id) {
        // 获取分享详情
        Share share = this.shareMapper.selectByPrimaryKey(id);
        // 发布人id
        Integer userId = share.getUserId();

        // 拿到所有用户中心实例信息
        List<ServiceInstance> instances = discoveryClient.getInstances("user-center");
        List<String> targetUrls = instances.stream().map(instance -> instance.getUri().toString() + "/users/{id}")
                .collect(Collectors.toList());
        // 使用RestTemplate调用
        // UserDTO userDTO1 = restTemplate.getForObject("http://user-center/users/{userId}", UserDTO.class, userId);
        
        // 使用FeignClient直接调用
        UserDTO userDTO = userCenterFeignClient.findById(userId);

        ShareDTO shareDTO = new ShareDTO();
        // 消息的装配
        BeanUtils.copyProperties(share, shareDTO);
        shareDTO.setWxNickname(userDTO.getWxNickname());
        return shareDTO;
    }

    /**
     * 审核分享内容
     * 审核通过后即发送MQ添加积分，利用Rocket MQ的事务特性
     */
    public Share auditById(Integer id, ShareAuditDTO auditDTO) {
        Share share = shareMapper.selectByPrimaryKey(id);
        if (share == null) {
            throw new IllegalArgumentException("参数非法！该分享不存在！");
        }
        if (!Objects.equals(share.getAuditStatus(), AuditStatusEnum.NOT_YET.getName())) {
            throw new IllegalArgumentException("参数非法！该分享已经审核过了！");
        }
        
        if (Objects.equals(auditDTO.getAuditStatusEnum(), AuditStatusEnum.PASS)) {
            // 审核通过：发送添加积分MQ，MQ封装的本地事务中执行审核通过操作
            String transactionId = UUID.randomUUID().toString();    // 事务id
            
            // 使用stream发送消息
            source.output().send(
                    MessageBuilder
                            .withPayload(new UserAddBonusMsgDto(share.getUserId(), 50))
                            .setHeader(RocketMQHeaders.TRANSACTION_ID, transactionId)  // 设置消息header
                            .setHeader("share_id", id)
                            .setHeader("dto", JSON.toJSONString(auditDTO))
                            .build()
            );
            
            /*
            【注意】审核通过状态的操作在MQ本地事务方法中，此处只执行发送消息逻辑，
            auditDTO额外参数就是在本地事务方法中执行审核的参数，详情见 AddBonusTransactionListener 类
             */
        } else {
            // 审核不通过：直接调用审核方法
            this.auditByIdInDB(id, auditDTO);
        }
        
        return share;
    }
    
    // 修改审核状态
    @Transactional(rollbackFor = Exception.class)
    public void auditByIdInDB(Integer id, ShareAuditDTO auditDTO) {
        Share share = Share.builder()
                .id(id)
                .auditStatus(auditDTO.getAuditStatusEnum().getName())
                .reason(auditDTO.getReason())
                .build();
        shareMapper.updateByPrimaryKeySelective(share);
    }

    // 修改审核状态，并记录rocketmq事务日志
    @Transactional(rollbackFor = Exception.class)
    public void auditByIdWithRocketMQLog(Integer id, ShareAuditDTO auditDTO, String transactionId) {
        // 审核状态
        this.auditByIdInDB(id, auditDTO);
        // 记录事务日志
        RocketmqTransactionLog transactionLog = RocketmqTransactionLog.builder()
                .transactionId(transactionId)
                .log("审核分享")
                .build();
        rocketmqTransactionLogMapper.insert(transactionLog);
    }
}
