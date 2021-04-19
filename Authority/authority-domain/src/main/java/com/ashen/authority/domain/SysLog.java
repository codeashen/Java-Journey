package com.ashen.authority.domain;

import com.ashen.authority.utils.DateUtils;
import lombok.Data;

import java.util.Date;

/**
 * 操作日志实体类
 */
@Data
public class SysLog {
    // 主键
    private String id;
    // 访问时间
    private Date visitTime;
    // 操作者用户名
    private String username;
    // 访问ip
    private String ip;
    // 访问资源url
    private String url;
    // 执行时长
    private Long executionTime;
    // 访问方法
    private String method;

    // 访问时间字符串
    private String visitTimeStr;

    /**
     * 设置访问时间，及其字符串形式
     * @param visitTime
     */
    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
        if (visitTime != null) {
            visitTimeStr = DateUtils.date2String(visitTime, "yyyy-MM-dd HH:mm");
        } else {
            visitTimeStr = null;
        }
    }
}
