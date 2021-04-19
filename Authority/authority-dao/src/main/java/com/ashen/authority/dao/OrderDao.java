package com.ashen.authority.dao;

import com.ashen.authority.domain.Member;
import com.ashen.authority.domain.Order;
import com.ashen.authority.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单持久层接口
 */
@Repository
public interface OrderDao {

    /**
     * 查询所有订单
     * 注解实现 订单-产品 一对一关系查询
     * @return
     */
    @Select("select * from authority_order")
    @Results(id = "orderMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId", property = "product", javaType = Product.class,
                    one = @One(select = "com.ashen.authority.dao.ProductDao.findById"))
    })
    List<Order> findAll();

    /**
     * 根据订单号查询订单详情
     * @param orderId
     * @return
     */
    @Select("select * from authority_order where id = #{orderId}")
    @Results(id = "fullMapper", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId", property = "product", javaType = Product.class,
                    one = @One(select = "com.ashen.authority.dao.ProductDao.findById")),
            @Result(column = "memberId", property = "member", javaType = Member.class,
                    one = @One(select = "com.ashen.authority.dao.MemberDao.findById")),
            @Result(column = "id", property = "travellers", javaType = List.class,
                    many = @Many(select = "com.ashen.authority.dao.TravellerDao.findByOrderId"))
    })
    Order findById(String orderId);
}
