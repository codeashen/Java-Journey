package com.ashen.authority.controller;

import com.ashen.authority.domain.Permission;
import com.ashen.authority.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 资源权限web层
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有资源权限
     * PreAuthorize的值表示，用户名为Ben或者拥有ROLE_ADMIN角色的用户，可以访问本方法
     * @return
     */
    @PreAuthorize("'Ben' == authentication.principal.username or hasAuthority('ROLE_ADMIN')")  // 方法级权限控制（方式三）
    @RequestMapping("/findAll")
    public ModelAndView findAll() {
        ModelAndView view = new ModelAndView("permission-list");
        List<Permission> list = permissionService.findAll();
        view.addObject("permissionList", list);
        return view;
    }

    /**
     * 添加资源权限操作
     * @param permission
     * @return
     */
    @RequestMapping("/save")
    public String save(Permission permission) {
        permissionService.save(permission);
        return "redirect:findAll";
    }
}
