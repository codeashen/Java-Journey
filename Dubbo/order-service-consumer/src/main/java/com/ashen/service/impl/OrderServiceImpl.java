package com.ashen.service.impl;

import com.ashen.domain.UserAddress;
import com.ashen.service.OrderService;
import com.ashen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Override
    public List<UserAddress> initOrder(String userId) {
        System.out.println("用户id：" + userId);
        // 调用生产者的方法，查询用户的收货地址
        List<UserAddress> addressList = userService.getUserAddressList(userId);

        for (UserAddress userAddress : addressList) {
            System.out.println(userAddress.getUserAddress());
        }
        return addressList;
    }
}
