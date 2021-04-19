package com.ashen.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * 自定义过滤器工厂，类名必须以 GatewayFilterFactory 结尾
 */
@Component
@Slf4j
public class PreLogGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        GatewayFilter gatewayFilter = (exchange, chain) -> {
            // 打印日志，获取配置文件配置的参数
            log.info("请求进来了，{}，{}", config.getName(), config.getValue());  //请求进来了，a，b

            // 获取并修改请求
            ServerHttpRequest modifiedRequest = exchange.getRequest()
                    .mutate()
                    .header("my-header", "header-value")
                    .build();
            // 修改exchange
            ServerWebExchange modifiedExchange = exchange.mutate()
                    .request(modifiedRequest)
                    .build();
            // 传递给下一个过滤器处理
            return chain.filter(modifiedExchange);
        };

        // （可选）用OrderedGatewayFilter包装，控制过滤器执行顺序，order数值小的先执行
        OrderedGatewayFilter filter = new OrderedGatewayFilter(gatewayFilter, 20);
        
        return filter;
    }
}
