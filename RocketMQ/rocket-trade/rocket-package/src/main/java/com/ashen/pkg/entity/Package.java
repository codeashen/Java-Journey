package com.ashen.pkg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_package")
public class Package implements Serializable {
    private static final long serialVersionUID = 5344803901254674518L;

    @Id
    @Column(name = "package_id")
    @GeneratedValue(generator = "JDBC")
    private String packageId;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "supplier_id")
    private String supplierId;

    @Column(name = "address_id")
    private String addressId;

    @Column(name = "remark")
    private String remark;

    @Column(name = "package_status")
    private String packageStatus;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
