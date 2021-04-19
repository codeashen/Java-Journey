package com.ashen.contentcenter.domain.dto.cotent;

import com.ashen.contentcenter.domain.enums.AuditStatusEnum;
import lombok.Data;

@Data
public class ShareAuditDTO {
    private AuditStatusEnum auditStatusEnum;
    private String reason;
}
