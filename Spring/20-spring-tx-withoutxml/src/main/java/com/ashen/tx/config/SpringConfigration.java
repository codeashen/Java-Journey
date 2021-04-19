package com.ashen.tx.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring的配置类，相当于bean.xml
 */
@Configuration  // 标志这是一个配置类
@ComponentScan("com.ashen.tx.withoutxml")  // 包扫描
@Import({JdbcConfig.class, TransactionConfig.class})  // 子配置类
@PropertySource("classpath:jdbcConfig.properties")  // 引入配置文件
@EnableTransactionManagement  // 开启注解配置事务支持
public class SpringConfigration {
}
