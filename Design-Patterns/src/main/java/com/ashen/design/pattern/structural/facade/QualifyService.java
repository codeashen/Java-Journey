package com.ashen.design.pattern.structural.facade;

/**
 * 积分兑换校验子系统
 */
public class QualifyService {

    // 校验是可以兑换礼物
    public boolean isAvailable(PointGift pointGift) {
        System.out.println("校验 " + pointGift.getName() + " 积分资格通过，库存通过");
        return true;
    }

}
