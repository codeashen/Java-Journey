package com.ashen.usercenter.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信小程序相关Bean配置
 */
@Configuration
public class WxConfig {
    /**
     * 小程序配置
     */
    @Bean
    public WxMaConfig wxMaConfig() {
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid("wx97f00565bb01f181");
        config.setSecret("6dab65ecb023ef88d43c3f63f2efd00c");
        return config;
    }
    
    /**
     * 小程序服务接口，提供各种api
     */
    @Bean
    public WxMaService wxMaService() {
        WxMaServiceImpl wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(wxMaConfig());
        return wxMaService;
    }
}
