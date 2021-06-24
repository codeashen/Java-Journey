package com.ashen.order.dao;

import com.ashen.order.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;

@Repository
public interface OrderMapper extends Mapper<Order> {
    int updateOrderStatus(@Param("orderId") String orderId, @Param("orderStatus") String orderStatus,
                          @Param("updateBy") String updateBy, @Param("updateTime") Date updateTime);
}
