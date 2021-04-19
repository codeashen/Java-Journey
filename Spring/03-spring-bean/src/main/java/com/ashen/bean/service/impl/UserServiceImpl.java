package com.ashen.bean.service.impl;

import com.ashen.bean.service.IUserService;

/**
 * 用户业务层接口实现类
 */
public class UserServiceImpl implements IUserService {


    public UserServiceImpl() {
        System.out.println("对象创建了。。。");
    }

    @Override
    public void saveUser() {
        System.out.println("service中的saveUser方法执行了。。。");
    }

    public void  init(){
        System.out.println("对象初始化了。。。");
    }
    public void  destroy(){
        System.out.println("对象销毁了。。。");
    }
}
