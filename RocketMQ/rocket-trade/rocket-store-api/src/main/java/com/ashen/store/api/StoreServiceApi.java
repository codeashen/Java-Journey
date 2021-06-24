package com.ashen.store.api;

import java.util.Date;

public interface StoreServiceApi {
    /**
     * 查询版本号
     *
     * @param supplierId 商户id
     * @param goodsId    商品id
     * @return
     */
    int selectVersion(String supplierId, String goodsId);

    /**
     * 更新库存
     *
     * @param version    版本号码
     * @param supplierId 商户id
     * @param goodsId    商品id
     * @param updateBy   更新人
     * @return
     */
    int updateStoreCountByVersion(int version, String supplierId, String goodsId, String updateBy, Date updateTime);

    /**
     * 查询库存
     *
     * @param supplierId 商户id
     * @param goodsId    商品id
     * @return
     */
    int selectStoreCount(String supplierId, String goodsId);
}
