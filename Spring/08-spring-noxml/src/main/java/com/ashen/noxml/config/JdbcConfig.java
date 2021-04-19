package com.ashen.noxml.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;

/**
 * 此配置类为子配置类，在SpringConfig中有引入
 */
public class JdbcConfig {

    @Value("${jdbc.driver}")  //SpEL表达式写入配置文件key
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String user;

    @Value("${jdbc.password}")
    private String password;

    /**
     * 创建一个QueryRunner对象，存入IoC容器
     * @param dataSource 参数是另一个带有Bean注解的返回值，参数上的Qualifier注解内指定数据源Bean上的值
     * @return
     */
    @Bean("runner")  // 将返回值存入IoC容器
    @Scope("prototype")  // 设置Bean为多例的
    public QueryRunner createQueryRunner(@Qualifier("ds1") DataSource dataSource) {
        //参数上的 @Qualifier("ds1")， 用于指定注入哪个数据源
        return new QueryRunner(dataSource);
    }

    /**
     * 数据源1
     * @return
     */
    @Bean("ds1")
    public DataSource createDataSource() {
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            // 直接使用Value注解导入的配置文件中的值
            ds.setDriverClass(driver);
            ds.setJdbcUrl(url);
            ds.setUser(user);
            ds.setPassword(password);
            return ds;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 数据源2
     * @return
     */
    @Bean("ds2")
    public DataSource createDataSource2() {
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass(driver);
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/db_study");
            ds.setUser(user);
            ds.setPassword(password);
            return ds;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
