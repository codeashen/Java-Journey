package com.ashen.common.config.database;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableTransactionManagement
@Slf4j
public class DruidDataSourceConfig {

    @Autowired
    private DruidDataSourceProperties druidProperties;

    /**
     * 数据源
     */
    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(druidProperties.getDriverClassName());
        ds.setUrl(druidProperties.getUrl());
        ds.setUsername(druidProperties.getUsername());
        ds.setPassword(druidProperties.getPassword());
        ds.setInitialSize(druidProperties.getInitialSize());
        ds.setMinIdle(druidProperties.getMinIdle());
        ds.setMaxIdle(druidProperties.getMaxIdle());
        ds.setMaxActive(druidProperties.getMaxActive());
        ds.setTimeBetweenEvictionRunsMillis(druidProperties.getTimeBetweenEvictionRunsMillis());
        ds.setMinEvictableIdleTimeMillis(druidProperties.getMinEvictableIdleTimeMillis());
        ds.setValidationQuery(druidProperties.getValidationQuery());
        ds.setTestWhileIdle(druidProperties.isTestWhileIdle());
        ds.setTestOnBorrow(druidProperties.isTestOnBorrow());
        ds.setTestOnReturn(druidProperties.isTestOnReturn());
        ds.setPoolPreparedStatements(druidProperties.isPoolPreparedStatements());
        ds.setMaxPoolPreparedStatementPerConnectionSize(druidProperties.getMaxPoolPreparedStatementPerConnectionSize());
        ds.setFilters(druidProperties.getFilters());
        ds.setConnectionProperties(druidProperties.getConnectionProperties());

        log.info(" druid datasource com.ashen.packaged.config : {} ", ds);
        return ds;
    }

    /**
     * 事务管理器
     */
    @Bean
    public PlatformTransactionManager transactionManager() throws Exception {
        DataSourceTransactionManager txManager = new DataSourceTransactionManager();
        txManager.setDataSource(dataSource());
        return txManager;
    }
}
