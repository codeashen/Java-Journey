package com.ashen.design.pattern.structural.decorator.v1;

/**
 * 加蛋的煎饼
 */
public class BatterCakeWithEgg extends BatterCake {
    @Override
    public String getDesc() {
        return super.getDesc() + " 加一个鸡蛋";
    }

    @Override
    public int cost() {
        return super.cost() + 1;
    }
}
