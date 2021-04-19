package com.ashen.proxy.cglib;

/**
 * 模拟一个生产厂家，无接口
 */
public class Producer {

    public void saleProduce(float money) {
        System.out.println("卖出产品，厂家拿到钱：" + money);
    }

    public void afterService(float money) {
        System.out.println("提供售后服务，厂家拿到钱：" + money);
    }
}
