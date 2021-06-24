package com.ashen.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = -1209546820744493185L;

    @Id
    @Column(name = "order_id")
    @GeneratedValue(generator = "JDBC")
    private String orderId;

    @Column(name = "order_type")
    private String orderType;

    @Column(name = "city_id")
    private String cityId;

    @Column(name = "platform_id")
    private String platformId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "supplier_id")
    private String supplierId;

    @Column(name = "goods_id")
    private String goodsId;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "remark")
    private String remark;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_time")
    private Date updateTime;
}
