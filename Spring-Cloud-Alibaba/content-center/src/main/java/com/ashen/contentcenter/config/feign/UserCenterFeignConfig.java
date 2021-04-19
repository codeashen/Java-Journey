package com.ashen.contentcenter.config.feign;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * Feign的配置类
 * 这个类别加@Configuration注解，否则必须挪到@ComponentScan包扫描以外，
 * 不然会有父子上下文问题，变成全局配置
 */
public class UserCenterFeignConfig {
    
    @Bean
    public Logger.Level level() {
        // 让Feign打印所有日志细节
        return Logger.Level.FULL;
    }
}
