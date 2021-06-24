package com.ashen.order.service.impl;

import com.ashen.order.constants.OrderStatus;
import com.ashen.order.dao.OrderMapper;
import com.ashen.order.entity.Order;
import com.ashen.order.service.OrderService;
import com.ashen.store.api.StoreServiceApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @DubboReference(check = false)
    private StoreServiceApi storeServiceApi;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public boolean createOrder(String cityId, String platformId, String userId, String supplierId, String goodsId) {
        boolean flag = true;
        try {
            Order order = new Order();
            order.setOrderId(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32));
            order.setOrderType("1");
            order.setCityId(cityId);
            order.setPlatformId(platformId);
            order.setUserId(userId);
            order.setSupplierId(supplierId);
            order.setGoodsId(goodsId);
            order.setOrderStatus(OrderStatus.ORDER_CREATED.value());
            order.setRemark("");
            Date currentTime = new Date();
            order.setCreateBy("admin");
            order.setCreateTime(currentTime);
            order.setUpdateBy("admin");
            order.setUpdateTime(currentTime);

            // 查询版本号，根据版本号更新库存-1（乐观锁）
            int currentVersion = storeServiceApi.selectVersion(supplierId, goodsId);
            int updatedCount = storeServiceApi.updateStoreCountByVersion(currentVersion, supplierId, goodsId, "admin", currentTime);
            
            if (updatedCount == 1) {
                // 库存更新成功插入订单
                orderMapper.insertSelective(order);
            } else if (updatedCount == 0) {
                // 库存更新失败两种情况：1库存不足；2乐观锁
                flag = false;    //	下单标识失败
                int currentStoreCount = storeServiceApi.selectStoreCount(supplierId, goodsId);
                if (currentStoreCount == 0) {
                    System.err.println("===== 库存不足 =====");
                } else {
                    System.err.println("===== 高并发乐观锁生效，版本号失效，更新库存失败 =====");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }

        return flag;
    }

    @Override
    public void sendOrderlyMessage4Pkg(String userId, String orderId) {
        
    }
}
