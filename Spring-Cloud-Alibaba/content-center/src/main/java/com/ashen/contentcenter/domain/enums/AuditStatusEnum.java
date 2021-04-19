package com.ashen.contentcenter.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审核状态枚举
 */
@Getter
@AllArgsConstructor
public enum AuditStatusEnum {
    NOT_YET("NOT_YET"), //待审核
    PASS("PASS"),       //通过
    REJECT("REJECT");   //不通过
    
    private final String name;
}
