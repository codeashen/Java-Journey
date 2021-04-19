package com.ashen.design.pattern.structural.decorator.v2;

/**
 * 实际的实体类
 */
public class BatterCake extends ABatterCake {
    @Override
    protected String getDesc() {
        return "煎饼";
    }

    @Override
    protected int cost() {
        return 8;
    }
}
