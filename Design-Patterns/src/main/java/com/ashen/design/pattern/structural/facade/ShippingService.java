package com.ashen.design.pattern.structural.facade;

/**
 * 物流子系统
 */
public class ShippingService {

    // 运输礼物，生成运单号
    public String shipGift(PointGift pointGift) {
        System.out.println(pointGift.getName() + " 进入物流系统");
        String shippingOrderNo = "66666";
        return shippingOrderNo;
    }
}
