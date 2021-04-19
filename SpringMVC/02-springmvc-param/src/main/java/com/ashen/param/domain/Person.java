package com.ashen.param.domain;

import java.util.Date;

/**
 * 用户实体类
 */
public class Person {

    private String name;
    private String age;
    // 演示自定义类型转换器参数绑定
    private Date birthday;
    // 演示对象中的对象参数绑定
    private IDCard idCard;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public IDCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IDCard idCard) {
        this.idCard = idCard;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", birthday=" + birthday +
                ", idCard=" + idCard +
                '}';
    }
}
