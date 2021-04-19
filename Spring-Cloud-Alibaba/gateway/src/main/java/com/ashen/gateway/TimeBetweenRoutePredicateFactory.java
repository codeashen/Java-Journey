package com.ashen.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义谓词工厂，类名必须以 RoutePredicateFactory 结尾
 */
@Component
@Slf4j
public class TimeBetweenRoutePredicateFactory extends AbstractRoutePredicateFactory<TimeBetweenConfig> {
    
    public TimeBetweenRoutePredicateFactory() {
        super(TimeBetweenConfig.class);
    }

    /**
     * 实现谓词判断的方法，谓词工厂核心方法
     * @param config 谓词工厂配置
     * @return 是否放行逻辑
     */
    @Override
    public Predicate<ServerWebExchange> apply(TimeBetweenConfig config) {
        LocalTime start = config.getStart();
        LocalTime end = config.getEnd();
        return exchange -> {
            LocalTime now = LocalTime.now();
            if (now.isAfter(start) && now.isBefore(end)) {
                return true;
            } else {
                log.warn("仅限{}到{}时间段内访问", start, end);
                return false;
            }
        };
    }

    /**
     * 控制配置类（TimeBetweenConfig）属性和配置文件中配置项（TimeBetween）的映射关系
     */
    @Override
    public List<String> shortcutFieldOrder() {
        /*
         * 例如我们的配置项是：TimeBetween=上午9:00, 下午5:00
         * 那么按照顺序，start对应的是上午9:00；end对应的是下午5:00
         */
        return Arrays.asList("start", "end");
    }

    // 谓词工厂配置时间的格式
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        System.out.println(formatter.format(LocalTime.now()));

    }
}
