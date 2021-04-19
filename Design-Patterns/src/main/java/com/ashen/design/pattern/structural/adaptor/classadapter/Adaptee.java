package com.ashen.design.pattern.structural.adaptor.classadapter;

/**
 * 被适配的类
 */
public class Adaptee {

    // 被适配类的解接口
    public void adapteeRequest() {
        System.out.println("被适配者的方法");
    }
}
