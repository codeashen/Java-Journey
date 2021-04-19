package com.ashen.design.pattern.structural.facade;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 积分礼物
 */
@Data
@AllArgsConstructor
public class PointGift {
    // 礼物名
    private String name;
    // 所需积分
    private Integer point;
}
