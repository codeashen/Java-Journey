package com.ashen.authority.service;

import com.ashen.authority.domain.Order;

import java.util.List;

/**
 * 订单业务层接口
 */
public interface OrderService {

    /**
     * 使用PageHelper分页查询订单
     * @param pageNum 页码
     * @param pageSize 每页容量
     * @return
     */
    List<Order> findAll(int pageNum, int pageSize);

    /**
     * 根据订单号查询订单详情
     * @param orderId
     * @return
     */
    Order findById(String orderId);
}
