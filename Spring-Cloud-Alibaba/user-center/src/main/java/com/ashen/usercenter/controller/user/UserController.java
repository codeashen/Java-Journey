package com.ashen.usercenter.controller.user;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.ashen.usercenter.domain.entity.user.User;
import com.ashen.usercenter.auth.CheckLogin;
import com.ashen.usercenter.domain.dto.user.JwtTokenRespDTO;
import com.ashen.usercenter.domain.dto.user.LoginRespDTO;
import com.ashen.usercenter.domain.dto.user.UserLoginDTO;
import com.ashen.usercenter.domain.dto.user.UserRespDTO;
import com.ashen.usercenter.service.user.UserService;
import com.ashen.usercenter.util.JwtOperator;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("users")
public class UserController {
    
    @Resource
    private UserService userService;
    @Resource
    private WxMaService wxMaService;
    @Resource
    private JwtOperator jwtOperator;
    
    @CheckLogin
    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        System.err.println("接口被访问，time=" + System.currentTimeMillis());
        return userService.findById(id);
    }

    /**
     * 微信登录注册
     */
    @PostMapping("/login")
    public LoginRespDTO login(@RequestBody UserLoginDTO loginDTO) throws WxErrorException {
        // 微信小程序服务端校验是否已登录
        // WxMaJscode2SessionResult result = wxMaService.getUserService().getSessionInfo(loginDTO.getCode());
        // 微信的OpenId，微信端唯一标识
        // String openid = result.getOpenid();
        
        String openid = "xxx";  // 注释上述代码，放开本行，接口调试用
        // 看用户是否注册，未注册就插入用户信息，注册了就颁发Token
        User user = userService.login(loginDTO, openid);
        
        // 颁发Token
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("id", user.getId());
        userInfo.put("wxNickname", user.getWxNickname());
        userInfo.put("role", user.getRoles());
        String token = jwtOperator.generateToken(userInfo);
        log.info("用户{}登录成功，生成token={}，有效期至{}", user.getWxNickname(), token, jwtOperator.getExpirationTime());
        
        return LoginRespDTO.builder()
                .user(UserRespDTO.builder()
                        .id(user.getId())
                        .avatarUrl(user.getAvatarUrl())
                        .bonus(user.getBonus())
                        .wxNickname(user.getWxNickname())
                        .build())
                .token(JwtTokenRespDTO.builder()
                        .expirationTime(jwtOperator.getExpirationTime().getTime())
                        .token(token)
                        .build())
                .build();
    }
}
