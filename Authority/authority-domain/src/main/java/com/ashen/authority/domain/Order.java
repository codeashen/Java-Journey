package com.ashen.authority.domain;

import com.ashen.authority.utils.DateUtils;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 */
@Data
public class Order {
    // 主键
    private String id;
    // 订单号
    private String orderNum;
    // 下单时间
    private Date orderTime;
    // 订单状态（0 未支付，1 已支付）
    private Integer orderStatus;
    // 出行人数
    private Integer peopleCount;
    // 支付方式（0 支付宝，1 微信，2 其他）
    private Integer payType;
    // 订单描述
    private String orderDesc;

    // 产品，一对一（旅游订单，一个订单一个产品）
    private Product product;
    // 会员（联系人）
    private Member member;
    // 旅客
    private List<Traveller> travellers;

    // 下单时间字符串
    private String orderTimeStr;
    // 订单状态字符串
    private String orderStatusStr;
    // 支付方式字符串
    private String payTypeStr;

    /**
     * 设置下单时间，及其字符串形式
     * @param orderTime
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
        if (orderTime != null) {
            orderTimeStr = DateUtils.date2String(orderTime, "yyyy-MM-dd HH:mm");
        }
    }

    /**
     * 设置订单状态，及其字符串形式
     * @param orderStatus
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
        if (orderStatus != null) {
            if (orderStatus == 0) {
                orderStatusStr = "未支付";
            }
            if (orderStatus == 1) {
                orderStatusStr = "已支付";
            }
        }
    }

    /**
     * 设置支付方式，及其字符串形式
     * @param payType
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
        if (payType != null) {
            if(payType==0){
                payTypeStr="支付宝";
            }else if(payType==1){
                payTypeStr="微信";
            }else if(payType==2){
                payTypeStr="其它";
            }
        }
    }
}
