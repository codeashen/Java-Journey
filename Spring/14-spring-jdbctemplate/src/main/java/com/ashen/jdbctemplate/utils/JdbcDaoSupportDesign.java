package com.ashen.jdbctemplate.utils;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 模拟Spring的JdbcDaoSupport的实现
 * 用于抽取dao实现类中的重复代码
 */
public class JdbcDaoSupportDesign {

    // 私有的，子类获取不到的
    private JdbcTemplate jdbcTemplate;

    /**
     * 供子类Dao获取JdbcTemplate，操作数据库
     * @return
     */
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 数据源的set方法，本质作用是初始化jdbcTemplate
     * Spring的set方式注入属性，会调用该类的set方法
     * 实现了该类的Dao，在Spring配置xml中，只需要set方式注入这个DataSource就可以初始化jdbcTemplate了
     * @param dataSource 数据源
     */
    public void setDataSource(DataSource dataSource) {
        if (jdbcTemplate == null) {
            jdbcTemplate = createJdbcTemplate(dataSource);
        }
    }

    /**
     * 根据传入数据源，创建一个JdbcTemplate对象
     * @param dataSource
     * @return
     */
    public JdbcTemplate createJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
