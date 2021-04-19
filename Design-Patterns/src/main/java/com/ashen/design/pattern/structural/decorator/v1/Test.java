package com.ashen.design.pattern.structural.decorator.v1;

public class Test {
    public static void main(String[] args) {
        BatterCake batterCake = new BatterCake();
        System.out.println(batterCake.getDesc() + ", 售价为:" + batterCake.cost());

        BatterCakeWithEgg batterCakeWithEgg = new BatterCakeWithEgg();
        System.out.println(batterCakeWithEgg.getDesc() + ", 售价为:" + batterCakeWithEgg.cost());

        BatterCakeWithEggSausage batterCakeWithEggSausage = new BatterCakeWithEggSausage();
        System.out.println(batterCakeWithEggSausage.getDesc() + ", 售价为:" + batterCakeWithEggSausage.cost());
    }
}
