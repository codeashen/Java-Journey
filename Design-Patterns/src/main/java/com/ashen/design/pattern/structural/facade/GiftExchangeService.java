package com.ashen.design.pattern.structural.facade;

/**
 * 外观类，组合各种子系统，外部只需要调用本外观类
 */
public class GiftExchangeService {
    private QualifyService qualifyService = new QualifyService();
    private PointsPaymentService pointsPaymentService = new PointsPaymentService();
    private ShippingService shippingService = new ShippingService();

    public void  giftExchange(PointGift pointGift) {
        if (qualifyService.isAvailable(pointGift)) {
            // 资格校验通过
            if (pointsPaymentService.pay(pointGift)) {
                // 支付积分成功
                String shippingOrderNo = shippingService.shipGift(pointGift);
                System.out.println("礼物正在路上,运单号:" + shippingOrderNo);
            }
        }
    }
}
