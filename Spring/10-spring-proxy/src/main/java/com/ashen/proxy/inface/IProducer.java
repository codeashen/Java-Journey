package com.ashen.proxy.inface;

/**
 * 对生产厂家要求的接口
 */
public interface IProducer {

    /**
     * 出售产品
     * @param money 产品价格
     */
    public void saleProduce(float money);

    /**
     * 售后服务
     * @param money 服务费
     */
    public void afterService(float money);
}
