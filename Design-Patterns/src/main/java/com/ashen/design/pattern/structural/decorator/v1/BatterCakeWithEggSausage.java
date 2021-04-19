package com.ashen.design.pattern.structural.decorator.v1;

/**
 * 加蛋加肠的煎饼
 */
public class BatterCakeWithEggSausage extends BatterCakeWithEgg {
    @Override
    public String getDesc() {
        return super.getDesc() + " 加一根香肠";
    }

    @Override
    public int cost() {
        return super.cost() + 1;
    }
}
