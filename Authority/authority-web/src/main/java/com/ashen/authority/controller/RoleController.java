package com.ashen.authority.controller;

import com.ashen.authority.domain.Permission;
import com.ashen.authority.domain.Role;
import com.ashen.authority.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 角色web层
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询所有角色
     * @return
     */
    @Secured("ROLE_ADMIN")  // 方法级权限控制（方式二）
    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {
        ModelAndView view = new ModelAndView("role-list");
        List<Role> roleList = roleService.findAll();
        view.addObject("roleList", roleList);
        return view;
    }

    /**
     * 添加角色操作
     * @param role
     * @return
     */
    @RequestMapping("/save")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:findAll";
    }

    /**
     * 根据id查询角色信息，以及该权限未关联的权限
     * @param roleId
     * @return
     */
    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id", required = true) String roleId) {
        ModelAndView view = new ModelAndView("role-permission-add");
        // 1.根据角色id查询角色信息
        Role role = roleService.findById(roleId);
        // 2.根据角色id查询该权限未关联的权限
        List<Permission> permissionList = roleService.findOtherPermission(roleId);

        view.addObject("role", role);
        view.addObject("permissionList", permissionList);
        return view;
    }

    /**
     * 给角色添加权限
     * @param roleId 角色id
     * @param permissionIds 要添加的权限id数组
     * @return
     */
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true) String roleId,
                                      @RequestParam(name = "ids", required = true) String[] permissionIds) {
        roleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:findAll";
    }
}
