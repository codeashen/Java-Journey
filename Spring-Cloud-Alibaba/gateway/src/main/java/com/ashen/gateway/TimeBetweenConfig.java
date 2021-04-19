package com.ashen.gateway;

import lombok.Data;

import java.time.LocalTime;

/**
 * 自定义谓词的配置类，属性对应配置文件内容
 */
@Data
public class TimeBetweenConfig {
    /**
     * 开始时间
     */
    private LocalTime start;
    /**
     * 结束时间
     */
    private LocalTime end;
}
