package com.ashen.paya.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "broker_message_log")
public class BrokerMessageLog {

    private static final long serialVersionUID = 3703603804794867916L;

    @Column(name = "message_id")
    private String messageId;

    @Column(name = "message")
    private String message;

    @Column(name = "try_count")
    private Integer tryCount;

    @Column(name = "status")
    private String status;

    @Column(name = "next_retry")
    private Date nextRetry;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
