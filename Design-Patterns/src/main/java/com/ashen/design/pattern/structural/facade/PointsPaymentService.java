package com.ashen.design.pattern.structural.facade;

/**
 * 积分支付子系统
 */
public class PointsPaymentService {

    // 支付积分方法
    public boolean pay(PointGift pointGift) {
        System.out.println("支付积分成功" + pointGift.getPoint());
        return true;
    }

}
