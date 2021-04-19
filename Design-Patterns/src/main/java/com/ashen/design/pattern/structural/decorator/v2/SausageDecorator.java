package com.ashen.design.pattern.structural.decorator.v2;

/**
 * 香肠装饰者
 */
public class SausageDecorator extends AbstractDecorator {
    public SausageDecorator(ABatterCake batterCake) {
        super(batterCake);
    }

    @Override
    protected void doSomething() {
        System.out.println("加热一根香肠");
    }

    @Override
    protected String getDesc() {
        this.doSomething();
        return super.getDesc() + " 加一根香肠";
    }

    @Override
    protected int cost() {
        return super.cost() + 1;
    }
}
