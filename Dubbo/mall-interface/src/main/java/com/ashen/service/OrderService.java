package com.ashen.service;

import com.ashen.domain.UserAddress;

import java.util.List;

/**
 * 订单业务层接口（服务消费者）
 */
public interface OrderService {

    /**
     * 初始化订单，初始化的时候会根据用户id，调用用户服务的获取用户收货地址
     * {@link UserService#getUserAddressList(String)}
     * @param userId 用户id
     */
    List<UserAddress> initOrder(String userId);
}
