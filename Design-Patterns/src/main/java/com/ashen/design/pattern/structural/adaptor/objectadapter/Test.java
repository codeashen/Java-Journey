package com.ashen.design.pattern.structural.adaptor.objectadapter;

/**
 * ITarget接口的具体实现通过Adapter已经移交给了Adaptee
 */
public class Test {
    public static void main(String[] args) {
        ITarget adapterTarget = new Adapter();
        adapterTarget.request();  // 被适配者的方法
    }
}
