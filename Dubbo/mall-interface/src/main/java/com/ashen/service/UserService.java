package com.ashen.service;

import com.ashen.domain.UserAddress;

import java.util.List;

/**
 * 用户业务层接口（服务提供者）
 */
public interface UserService {

    /**
     * 按照用户id返回所有的收货地址
     */
    List<UserAddress> getUserAddressList(String userId);
}
