package com.ashen.di.service.impl;

import com.ashen.di.service.IUserService;

import java.util.Date;

/**
 * 用户业务层接口实现类
 */
public class UserServiceImpl2 implements IUserService {

    private String name;
    private Integer age;
    private Date birthday;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
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
