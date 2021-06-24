package com.ashen.store.entity;


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
@Table(name = "t_store")
public class Store implements Serializable {

    @Id
    @Column(name = "store_id")
    @GeneratedValue(generator = "JDBC")
    private String storeId;

    @Column(name = "goods_id")
    private String goodsId;

    @Column(name = "supplier_id")
    private String supplierId;

    @Column(name = "goods_name")
    private String goodsName;

    @Column(name = "store_count")
    private Integer storeCount;

    @Column(name = "version")
    private Integer version;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_time")
    private Date updateTime;
}
