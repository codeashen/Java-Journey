package com.ashen.order.service;

public interface OrderService {

    /**
     * 创建订单
     */
    boolean createOrder(String cityId, String platformId, String userId, String supplierId, String goodsId);

    /**
     * 发送顺序消息
     *
     * @param userId  用户id
     * @param orderId 订单id
     */
    void sendOrderlyMessage4Pkg(String userId, String orderId);
}
