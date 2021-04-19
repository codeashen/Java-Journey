package com.ashen.contentcenter.config.ribbon;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * 使用java代码的方式配置ribbon
 * 配置负载均衡的服务，和负载均衡策略
 * 表示请求user-center服务时，会使用RibbonConfig指定的策略进行负载均衡
 */
@Configuration
@RibbonClient(name = "user-center", configuration = NacosSameClusterWeightedRule.class)  //单个服务配置
// @RibbonClients(defaultConfiguration = RibbonConfig.class)  //全局配置
public class UserCenterRibbonConfig {
}
