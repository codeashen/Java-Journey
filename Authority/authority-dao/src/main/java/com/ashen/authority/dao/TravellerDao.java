package com.ashen.authority.dao;

import com.ashen.authority.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 旅客持久层接口
 */
@Repository
public interface TravellerDao {

    /**
     * 根据订单号查询改单下的所有旅客
     * @param OrderId
     * @return
     */
    @Select("select * from authority_traveller where id in (" +
            "select travellerId from authority_order_traveller where orderId = #{orderId})")
    List<Traveller> findByOrderId(String OrderId);
}
