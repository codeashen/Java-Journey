package com.ashen.usercenter.domain.dto.mq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 添加积分消息DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAddBonusMsgDto {
    // 积分添加人
    private Integer userId;
    // 添加积分数
    private Integer bonus;
}
