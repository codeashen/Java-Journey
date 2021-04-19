package com.ashen.bean.factory;

import com.ashen.bean.service.impl.UserServiceImpl;
import com.ashen.bean.service.IUserService;

/**
 * 模拟一个工厂类（该类可能是存在于jar包中的，我们无法通过修改源码的方式来提供默认构造函数）
 */
public class InstanceFactory {

    public IUserService getUserService(){
        return new UserServiceImpl();
    }
}
