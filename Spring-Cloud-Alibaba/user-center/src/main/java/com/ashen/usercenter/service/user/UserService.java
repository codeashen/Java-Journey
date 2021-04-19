package com.ashen.usercenter.service.user;

import com.ashen.usercenter.dao.user.UserMapper;
import com.ashen.usercenter.domain.entity.bonus.BonusEventLog;
import com.ashen.usercenter.domain.entity.user.User;
import com.ashen.usercenter.dao.bonus.BonusEventLogMapper;
import com.ashen.usercenter.domain.dto.mq.UserAddBonusMsgDto;
import com.ashen.usercenter.domain.dto.user.UserLoginDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_={@Autowired})  //表示在构造方法上加上哪些注解
public class UserService {
    
    private final UserMapper userMapper;
    private final BonusEventLogMapper bonusEventLogMapper;

    public User findById(Integer id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加积分，由MQ消费者调用
     * @param userAddBonusMsgDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void addBonus(UserAddBonusMsgDto userAddBonusMsgDto) {
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

    /**
     * 登录/注册
     * @param loginDTO
     * @param openId
     * @return
     */
    public User login(UserLoginDTO loginDTO, String openId) {
        User user = userMapper.selectOne(
                User.builder().wxId(openId).build());
        
        if (user == null) {
            User userToSave = User.builder()
                    .wxId(openId).wxNickname(loginDTO.getWxNickname()).avatarUrl(loginDTO.getAvatarUrl())
                    .bonus(300).roles("user").createTime(new Date()).updateTime(new Date())
                    .build();
            userMapper.insertSelective(userToSave);
            return userToSave;
        }
        
        return user;
    }
}
