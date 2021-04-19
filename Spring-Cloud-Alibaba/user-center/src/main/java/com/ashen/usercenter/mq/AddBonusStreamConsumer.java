package com.ashen.usercenter.mq;

import com.ashen.usercenter.dao.user.UserMapper;
import com.ashen.usercenter.dao.bonus.BonusEventLogMapper;
import com.ashen.usercenter.domain.dto.mq.UserAddBonusMsgDto;
import com.ashen.usercenter.domain.entity.bonus.BonusEventLog;
import com.ashen.usercenter.domain.entity.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Slf4j
public class AddBonusStreamConsumer {

    @Resource
    private UserMapper userMapper;
    @Resource
    private BonusEventLogMapper bonusEventLogMapper;
    
    @StreamListener(Sink.INPUT)
    public void receive(UserAddBonusMsgDto userAddBonusMsgDto) {
        // 添加积分
        Integer userId = userAddBonusMsgDto.getUserId();
        User user = userMapper.selectByPrimaryKey(userId);
        user.setBonus(user.getBonus() + userAddBonusMsgDto.getBonus());
        userMapper.updateByPrimaryKey(user);

        // 记录日志
        bonusEventLogMapper.insert(
                BonusEventLog.builder()
                        .userId(userId)
                        .value(userAddBonusMsgDto.getBonus())
                        .event("CONTRIBUTE")
                        .createTime(new Date())
                        .description("投稿添加积分")
                        .build()
        );

        log.info("积分添加完成");
    }
    
}
