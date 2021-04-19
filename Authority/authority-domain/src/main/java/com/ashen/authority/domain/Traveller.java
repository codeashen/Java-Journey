package com.ashen.authority.domain;

import lombok.Data;

/**
 * 旅客实体类
 */
@Data
public class Traveller {
    // 主键
    private String id;
    // 姓名
    private String name;
    // 性别
    private String sex;
    // 电话号码
    private String phoneNum;
    // 证件类型（0 身份证，1 护照，3 军官证）
    private Integer credentialsType;
    // 证件号码
    private String credentialsNum;
    // 旅客类型（0 成人，1 儿童）
    private Integer travellerType;

    // 证件类型字符串
    private String credentialsTypeStr;
    // 旅客类型字符串
    private String travellerTypeStr;

    /**
     * 设置证件类型,及其字符串形式
     * @param credentialsType
     */
    public void setCredentialsType(Integer credentialsType) {
        this.credentialsType = credentialsType;
        if (credentialsType != null) {
            if (credentialsType == 0) {
                credentialsTypeStr = "身份证";
            }
            if (credentialsType == 1) {
                credentialsTypeStr = "护照";
            }
            if (credentialsType == 2) {
                credentialsTypeStr = "军官证";
            }
        }
    }

    /**
     * 设置 旅客类型，及其字符串形式
     * @param travellerType
     */
    public void setTravellerType(Integer travellerType) {
        this.travellerType = travellerType;
        if (travellerType != null) {
            if (travellerType == 0) {
                travellerTypeStr = "成人";
            }
            if (travellerType == 1) {
                travellerTypeStr = "儿童";
            }
        }
    }
}
