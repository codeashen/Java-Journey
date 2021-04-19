package com.ashen.design.pattern.creational.prototype.abstractprototype;

/**
 * B类继承抽象类A，可以被克隆
 */
public class B extends A {

    public static void main(String[] args) throws CloneNotSupportedException {
        B b = new B();
        B b1 = (B) b.clone();
    }

}
