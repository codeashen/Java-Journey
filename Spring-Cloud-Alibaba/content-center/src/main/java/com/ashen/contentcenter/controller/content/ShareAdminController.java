package com.ashen.contentcenter.controller.content;

import com.ashen.contentcenter.domain.dto.cotent.ShareAuditDTO;
import com.ashen.contentcenter.domain.entity.content.Share;
import com.ashen.contentcenter.service.content.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/shares")
public class ShareAdminController {
    
    @Autowired
    private ShareService shareService;
    
    @PutMapping("/audit/{id}")
    public Share auditById(@PathVariable Integer id, @RequestBody ShareAuditDTO auditDTO) {
        // TODO 认证、授权
        return shareService.auditById(id, auditDTO);
    }
}
