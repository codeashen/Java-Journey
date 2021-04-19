package com.ashen.proxy.inface;

/**
 * 模拟一个生产厂家，实现了生产厂家接口
 */
public class Producer implements IProducer {
    @Override
    public void saleProduce(float money) {
        System.out.println("卖出产品，厂家拿到钱：" + money);
    }

    @Override
    public void afterService(float money) {
        System.out.println("提供售后服务，厂家拿到钱：" + money);
    }
}
