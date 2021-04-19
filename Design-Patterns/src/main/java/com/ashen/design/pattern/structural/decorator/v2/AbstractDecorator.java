package com.ashen.design.pattern.structural.decorator.v2;

/**
 * 抽象的装饰者
 */
public abstract class AbstractDecorator extends ABatterCake {

    private ABatterCake batterCake;

    public AbstractDecorator(ABatterCake batterCake) {
        this.batterCake = batterCake;
    }

    // 可以在抽象装饰者中加入抽象方法，让实际装饰者重写，如果没有抽象方法，可以要抽象装饰者，直接定义装饰者类
    protected abstract void doSomething();

    @Override
    protected String getDesc() {
        return batterCake.getDesc();
    }

    @Override
    protected int cost() {
        return batterCake.cost();
    }
}
