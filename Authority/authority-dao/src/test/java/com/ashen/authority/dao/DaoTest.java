package com.ashen.authority.dao;

import com.ashen.authority.domain.Order;
import com.ashen.authority.domain.Traveller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-dao.xml")
public class DaoTest {

    @Autowired
    private OrderDao orderDao;

    @Test
    public void findAllOrder() {
        List<Order> list = orderDao.findAll();
        for (Order order : list) {
            System.out.println(order);
        }
    }

    @Test
    public void findOrderById() {
        Order order = orderDao.findById("0E7231DC797C486290E8713CA3C6ECCC");
        System.out.println("============ 产品 ============");
        System.out.println(order.getProduct());
        System.out.println("============ 会员 ============");
        System.out.println(order.getMember());
        System.out.println("============ 旅客 ============");
        for (Traveller traveller : order.getTravellers()) {
            System.out.println(traveller);
        }
    }
}