package com.ashen.di.service.impl;

import com.ashen.di.service.IUserService;

import java.util.Date;

/**
 * 用户业务层接口实现类
 */
public class UserServiceImpl implements IUserService {

    // 如果是经常变化的数据，并不适用于注入的方式
    private String name;
    private Integer age;
    private Date birthday;

    public UserServiceImpl(String name, Integer age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    @Override
    public void saveUser() {
        System.out.println("service中的saveUser方法执行了。。。");
        System.out.println(name);
        System.out.println(age);
        System.out.println(birthday);
    }

}
