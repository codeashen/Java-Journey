package com.ashen.noxml.test;

import com.ashen.noxml.config.SpringConfig;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试QueryRunner是否为多例
 * 基于注解配置类创建ApplicationContext
 */
public class QueryRunnerTest {

    @Test
    public void testAnnoContext() {
        // 1.注解配置类创建ApplicationContext
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        // 2.获取Bean
        QueryRunner runner1 = context.getBean("runner", QueryRunner.class);
        QueryRunner runner2 = context.getBean("runner", QueryRunner.class);

        System.out.println(runner1 == runner2);  // false
    }
}
