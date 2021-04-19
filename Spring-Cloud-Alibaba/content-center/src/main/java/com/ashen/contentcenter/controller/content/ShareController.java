package com.ashen.contentcenter.controller.content;

import com.ashen.contentcenter.domain.dto.cotent.ShareDTO;
import com.ashen.contentcenter.service.content.ShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shares")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ShareController {
    private final ShareService shareService;

    @GetMapping("/{id}")
    public ShareDTO findById(@PathVariable Integer id) {
        return this.shareService.findById(id);
    }
}
