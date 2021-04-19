package com.ashen.design.pattern.structural.adaptor.classadapter;

/**
 * 适配器继承被适配的类，实现期望调用接口
 */
public class Adapter extends Adaptee implements ITarget {

    @Override
    public void request() {
        // 调用前逻辑...
        super.adapteeRequest();
        // 调用后逻辑...
    }
}
