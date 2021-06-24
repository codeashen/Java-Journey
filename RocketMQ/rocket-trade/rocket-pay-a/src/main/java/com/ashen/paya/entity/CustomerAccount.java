package com.ashen.paya.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_customer_account")
public class CustomerAccount implements Serializable {

    private static final long serialVersionUID = -7331676624814910893L;

    @Id
    @Column(name = "account_id")
    @GeneratedValue(generator = "JDBC")
    private String accountId;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "date_time")
    private Date dateTime;

    @Column(name = "current_balance")
    private BigDecimal currentBalance;

    @Column(name = "version")
    private Integer version;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
