package com.ashen.lazy.domain;

import java.io.Serializable;

/**
 * 【一对一】   一个Account属于一个User
 * 解释：
 *      此关系中，应该是  账户---用户   n---1
 *      Mybatis把【多对一】区别于【一对多】
 *      Mybatis把【多对一】看作是一对一，一个Account属于一个User
 */
public class Account implements Serializable {

    private Integer id;
    private Integer uid;
    private Double money;

    // 从表实体应该包含一个主表实体的对象引用
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", uid=" + uid +
                ", money=" + money +
                '}';
    }
}
