/*=========================== 支付库 A ===========================*/
CREATE DATABASE rocket_paya; 
USE rocket_paya;
CREATE TABLE t_customer_account
(
    account_id      VARCHAR(32) NOT NULL COMMENT '账户id',
    account_no      VARCHAR(32) COMMENT '账号',
    date_time       TIMESTAMP COMMENT '余额日期',
    current_balance DECIMAL(15, 2) COMMENT '当前余额',
    `version`       INT(10) COMMENT '版本号',
    create_time     TIMESTAMP   NOT NULL COMMENT '创建时间',
    update_time     TIMESTAMP   NOT NULL COMMENT '更新时间',
    PRIMARY KEY (account_id)
) COMMENT '用户余额表';


/*=========================== 支付库 B ===========================*/
CREATE DATABASE rocket_payb; 
USE rocket_payb;
CREATE TABLE t_platform_account
(
    account_id      VARCHAR(32) NOT NULL COMMENT '账户id',
    account_no      VARCHAR(32) COMMENT '账号',
    date_time       TIMESTAMP COMMENT '余额日期',
    current_balance DECIMAL(15, 2) COMMENT '当前余额',
    `version`       INT(10) COMMENT '版本号',
    create_time     TIMESTAMP   NOT NULL COMMENT '创建时间',
    update_time     TIMESTAMP   NOT NULL COMMENT '更新时间',
    PRIMARY KEY (account_id)
) COMMENT '平台余额表';


/*============================ 订单库 ============================*/
CREATE DATABASE rocket_order; 
USE rocket_order;
CREATE TABLE t_order
(
    order_id     VARCHAR(32) NOT NULL COMMENT '订单id',
    order_type   VARCHAR(10) COMMENT '订单类型',
    city_id      VARCHAR(32) COMMENT '城市id',
    platform_id  VARCHAR(32) COMMENT '平台id',
    user_id      VARCHAR(32) COMMENT '用户id',
    supplier_id  VARCHAR(32) COMMENT '商户id',
    goods_id     VARCHAR(32) COMMENT '商品id',
    order_status VARCHAR(32) COMMENT '订单状态',
    remark       VARCHAR(200) COMMENT '备注',
    create_by    VARCHAR(50) NOT NULL COMMENT '创建人',
    create_time  TIMESTAMP   NOT NULL COMMENT '创建时间',
    update_by    VARCHAR(50) NOT NULL COMMENT '更新人',
    update_time  TIMESTAMP   NOT NULL COMMENT '更新时间',
    PRIMARY KEY (order_id)
) COMMENT '订单表';


/*============================ 库存库 ============================*/
CREATE DATABASE rocket_store; 
USE rocket_store;
CREATE TABLE t_store
(
    store_id    VARCHAR(32) NOT NULL COMMENT '库存id',
    goods_id    VARCHAR(32) COMMENT '商品id',
    supplier_id VARCHAR(32) COMMENT '商户id',
    goods_name  VARCHAR(40) COMMENT '商品名称',
    store_count INT(10) NOT NULL COMMENT '库存数量',
    `version`   INT(10) NOT NULL COMMENT '版本号',
    create_by   VARCHAR(50) NOT NULL COMMENT '创建人',
    create_time TIMESTAMP   NOT NULL COMMENT '创建时间',
    update_by   VARCHAR(50) NOT NULL COMMENT '更新人',
    update_time TIMESTAMP   NOT NULL COMMENT '更新时间',
    PRIMARY KEY (store_id)
) COMMENT '库存表';


/*============================ 物流库 ============================*/
CREATE DATABASE rocket_package; 
USE rocket_package;
CREATE TABLE t_package
(
    package_id     VARCHAR(32) NOT NULL COMMENT '包裹id',
    order_id       VARCHAR(32) COMMENT '订单id',
    supplier_id    VARCHAR(32) COMMENT '商户id',
    address_id     VARCHAR(32) COMMENT '地址id',
    remark         VARCHAR(40) COMMENT '备注',
    package_status VARCHAR(10) COMMENT '包裹状态',
    create_time    TIMESTAMP   NOT NULL COMMENT '创建时间',
    update_time    TIMESTAMP   NOT NULL COMMENT '更新时间',
    PRIMARY KEY (package_id)
) COMMENT '物流表';


/*============================ MQ消息 ============================*/
CREATE DATABASE rocket_msg; 
USE rocket_msg;
CREATE TABLE broker_message_log
(
    message_id  VARCHAR(32) NOT NULL COMMENT '消息id',
    message     VARCHAR(400) COMMENT '消息内容',
    try_count   INT(5) COMMENT '重试次数',
    `status`    VARCHAR(10) COMMENT '消息状态',
    next_retry  TIMESTAMP COMMENT '下次重试时间',
    create_time TIMESTAMP   NOT NULL COMMENT '创建时间',
    update_time TIMESTAMP   NOT NULL COMMENT '更新时间',
    PRIMARY KEY (message_id)
) COMMENT '消息记录表';

