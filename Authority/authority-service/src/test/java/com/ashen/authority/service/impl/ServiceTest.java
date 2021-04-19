package com.ashen.authority.service.impl;

import com.ashen.authority.domain.Order;
import com.ashen.authority.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext-service.xml", "classpath*:applicationContext-dao.xml"})
public class ServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void findAllOrder() {
        List<Order> list = orderService.findAll(1, 4);
        System.out.println("查出" + list.size() + "条订单");
        for (Order order : list) {
            System.out.println(order);
        }
    }
}