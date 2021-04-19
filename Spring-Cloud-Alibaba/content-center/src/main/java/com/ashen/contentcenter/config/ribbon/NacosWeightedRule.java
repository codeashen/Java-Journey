package com.ashen.contentcenter.config.ribbon;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义基于Nacos权重的ribbon IRule
 * 自定义IRule可以实现IRule接口或继承AbstractLoadBalancerRule抽象类
 * 权重可以在nacos server控制台设置
 */
@Slf4j
public class NacosWeightedRule extends AbstractLoadBalancerRule {
    
    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;
    
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
    }

    @Override
    public Server choose(Object key) {
        try {
            // 获取负载均衡器
            BaseLoadBalancer loadBalancer = (BaseLoadBalancer) this.getLoadBalancer();
            // 获取请求的服务名
            String name = loadBalancer.getName();
            // 拿到服务发现相关的API
            NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();
            // nacos client自动通过基于权重的负载均衡算法，选择一个服务实例
            Instance instance = namingService.selectOneHealthyInstance(name);
            log.info("选择实例是 {}:{}", instance.getIp(), instance.getPort());
            return new NacosServer(instance);
        } catch (NacosException e) {
            log.error("获取服务实例出错", e);
            return null;
        }
    }
}
