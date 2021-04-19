package com.ashen.authority.domain;

import com.ashen.authority.utils.DateUtils;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 产品实体类
 */
@Data
public class Product {
    // 主键
    private String id;
    // 编号 唯一
    private String productNum;
    // 名称
    private String productName;
    // 出发城市
    private String cityName;
    // 出发时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")  // Spring提供的类型转换注解
    private Date departureTime;
    // 产品价格
    private double productPrice;
    // 产品描述
    private String productDesc;
    // 状态 0 关闭 1 开启
    private Integer productStatus;

    // 出发时间字符串（附加）
    private String departureTimeStr;
    // 状态字符串描述（附加）
    private String productStatusStr;

    /**
     * 设置出发时间，及其字符串形式
     * @param departureTime
     */
    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
        if (departureTime != null) {
            departureTimeStr =  DateUtils.date2String(this.departureTime, "yyyy-MM-dd HH:mm");
        }
    }

    /**
     * 设置产品状态，及其字符串形式
     * @param productStatus
     */
    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
        if (productStatus != null) {
            if (productStatus == 0) {
                productStatusStr = "关闭";
            }
            if (productStatus == 1) {
                productStatusStr = "开启";
            }
        }
    }
}
