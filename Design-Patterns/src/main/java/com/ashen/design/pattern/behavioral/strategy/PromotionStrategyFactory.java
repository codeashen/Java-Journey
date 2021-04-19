package com.ashen.design.pattern.behavioral.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 促销策略工厂
 */
public class PromotionStrategyFactory {

    // 存放所有的促销策略
    private static final Map<String, PromotionStrategy> PROMOTION_STRATEGY_MAP = new HashMap<>();

    // 无对应促销策略时返回的默认值
    private static final PromotionStrategy NON_PROMOTION_STRATEGY = () -> System.out.println("无促销活动");

    // 静态代码块初始化促销策略map
    static {
        PROMOTION_STRATEGY_MAP.put("LIJIAN", new LiJianPromotionStrategy());
        PROMOTION_STRATEGY_MAP.put("MANJIAN", new ManJianPromotionStrategy());
        PROMOTION_STRATEGY_MAP.put("FANXIAN", new FanXianPromotionStrategy());
    }

    private PromotionStrategyFactory() {
    }

    public static PromotionStrategy getPromotionStrategy(String promotionKey) {
        PromotionStrategy promotionStrategy = PROMOTION_STRATEGY_MAP.get(promotionKey);
        return promotionStrategy != null ? promotionStrategy : NON_PROMOTION_STRATEGY;
    }
}
