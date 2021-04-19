package com.ashen.tx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 事务相关的配置类
 */
public class TransactionConfig {

    /**
     * 创建事务管理器对象，存入IoC容器
     * @param dataSource 数据源
     * @return 返回值设置为Spring事务管理器接口，PlatformTransactionManager，
     *         实际返回值为接口实现类DataSourceTransactionManager
     */
    @Bean("transactionManager")
    public PlatformTransactionManager createTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
