package com.ashen.design.pattern.creational.prototype.abstractprototype;

/**
 * 实现了Cloneable接口的抽象类，继承这抽象类的类对象都可以被克隆
 */
public abstract class A implements Cloneable {
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
