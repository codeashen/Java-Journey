package com.ashen.design.pattern.structural.decorator.v2;

/**
 * 鸡蛋装饰着
 */
public class EggDecorator extends AbstractDecorator {
    public EggDecorator(ABatterCake batterCake) {
        super(batterCake);
    }

    @Override
    protected void doSomething() {
        System.out.println("打匀一个鸡蛋");
    }

    @Override
    protected String getDesc() {
        this.doSomething();
        return super.getDesc() + " 加一个鸡蛋";
    }

    @Override
    protected int cost() {
        return super.cost() + 1;
    }
}
