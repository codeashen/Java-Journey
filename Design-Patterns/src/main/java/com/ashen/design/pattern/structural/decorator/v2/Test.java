package com.ashen.design.pattern.structural.decorator.v2;

public class Test {
    public static void main(String[] args) {
        ABatterCake batterCake = new BatterCake();

        batterCake = new EggDecorator(batterCake);
        batterCake = new SausageDecorator(batterCake);

        System.out.println(batterCake.getDesc() + ", 售价为:" + batterCake.cost());
    }
}