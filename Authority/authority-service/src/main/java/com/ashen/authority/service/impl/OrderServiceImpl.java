package com.ashen.authority.service.impl;

import com.github.pagehelper.PageHelper;
import com.ashen.authority.dao.OrderDao;
import com.ashen.authority.domain.Order;
import com.ashen.authority.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单业务层接口实现类
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> findAll(int pageNum, int pageSize) {
        // PageHelper开启分页，必须写在执行查询的代码之前，中间别写其他代码
        PageHelper.startPage(pageNum, pageSize);
        return orderDao.findAll();
    }

    @Override
    public Order findById(String orderId) {
        return orderDao.findById(orderId);
    }
}
