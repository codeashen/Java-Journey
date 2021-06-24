package com.ashen.store.service.provider;

import com.ashen.store.api.StoreServiceApi;
import com.ashen.store.dao.StoreMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@DubboService
public class StoreServiceProvider implements StoreServiceApi {

    @Autowired
    private StoreMapper storeMapper;

    @Override
    public int selectVersion(String supplierId, String goodsId) {
        return storeMapper.selectVersion(supplierId, goodsId);
    }

    @Override
    public int updateStoreCountByVersion(int version, String supplierId, String goodsId, String updateBy, Date updateTime) {
        return storeMapper.updateStoreCountByVersion(version, supplierId, goodsId, updateBy, updateTime);
    }

    @Override
    public int selectStoreCount(String supplierId, String goodsId) {
        return storeMapper.selectStoreCount(supplierId, goodsId);
    }
}
