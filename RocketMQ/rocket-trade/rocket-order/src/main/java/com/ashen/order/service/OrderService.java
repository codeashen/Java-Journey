package com.ashen.order.service;

import com.ashen.order.entity.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    boolean createOrder(String cityId, String platformId, String userId, String supplierId, String goodsId);

    void sendOrderlyMessage4Pkg(String userId, String orderId);
}
