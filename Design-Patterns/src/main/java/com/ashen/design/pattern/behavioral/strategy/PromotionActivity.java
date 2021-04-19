package com.ashen.design.pattern.behavioral.strategy;

import lombok.RequiredArgsConstructor;

/**
 * 促销活动类，组合入促销策略类
 */
@RequiredArgsConstructor
public class PromotionActivity {

    // 促销策略
    private final PromotionStrategy promotionStrategy;

    // 实行促销方法
    public void executePromotionStrategy(){
        promotionStrategy.doPromotion();
    }
}
