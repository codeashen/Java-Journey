package com.ashen.authority.controller;

import com.ashen.authority.domain.Role;
import com.ashen.authority.domain.UserInfo;
import com.ashen.authority.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * 用户Web层
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll() {
        ModelAndView view = new ModelAndView("user-list");
        List<UserInfo> userList = userService.findAll();
        view.addObject("userList", userList);
        return view;
    }

    /**
     * 添加用户
     * @param userInfo
     * @return
     */
    @RequestMapping("/save")
    public String save(UserInfo userInfo) {
        userService.save(userInfo);
        return "redirect:findAll";
    }

    /**
     * 根据用户id查询用户详情
     * 加上RolesAllowed注解表示只有注解中的角色才可以访问本方法
     * @param id
     * @return
     */
    @RolesAllowed("ROLE_ADMIN")  // 方法级权限控制（方式一）
    @RequestMapping("/findById")
    public ModelAndView findById(String id) {
        ModelAndView view = new ModelAndView("user-show");
        UserInfo userInfo = userService.findById(id);
        view.addObject("user",userInfo);
        return view;
    }

    /**
     * 根据id查询用户信息，以及该用户未拥有的角色
     * @param userId
     * @return
     */
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) String userId) {
        ModelAndView view = new ModelAndView("user-role-add");
        // 1.根据用户id查询用户信息
        UserInfo userInfo = userService.findById(userId);
        // 2.根据用户id查询用户未拥有的角色
        List<Role> roleList = userService.findOtherRoles(userId);

        view.addObject("user", userInfo);
        view.addObject("roleList", roleList);
        return view;
    }

    /**
     * 给用户添加角色
     * @param userId 用户id
     * @param roleIds 要添加的角色id数组
     * @return
     */
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) String userId,
                                @RequestParam(name = "ids", required = true) String[] roleIds) {
        userService.addRoleToUser(userId, roleIds);
        return "redirect:findAll.do";
    }
}
