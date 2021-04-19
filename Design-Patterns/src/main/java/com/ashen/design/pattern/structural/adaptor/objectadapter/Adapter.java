package com.ashen.design.pattern.structural.adaptor.objectadapter;

/**
 * 适配器中组合被适配对象Adaptee对象
 */
public class Adapter implements ITarget {

    private Adaptee adaptee = new Adaptee();

    @Override
    public void request() {
        // 调用前逻辑...
        adaptee.adapteeRequest();
        // 调用后逻辑...
    }
}
