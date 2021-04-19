package com.ashen.service.impl;

import com.ashen.domain.UserAddress;
import com.ashen.service.UserService;

import java.util.Arrays;
import java.util.List;

/**
 * 用户业务层接口实现类，服务提供者具体实现
 */
public class UserServiceImpl implements UserService {

    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        System.out.println("UserServiceImpl...");
        UserAddress address1 = new UserAddress(1, "上海青浦区徐泾镇明珠路1018号", "1", "李老师", "010-56253825", "Y");
        UserAddress address2 = new UserAddress(2, "山西省太原市尖草坪区上兰帝国皇家学院", "1", "王老师", "010-56253825", "N");
        return Arrays.asList(address1, address2);
    }
}
