package ribbonconfig;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ribbon负载均衡规则，此配置类必须不能被Spring注解扫描到，因为存在父子上下文问题
 */
@Configuration
public class RibbonConfig {
    
    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
    
}
