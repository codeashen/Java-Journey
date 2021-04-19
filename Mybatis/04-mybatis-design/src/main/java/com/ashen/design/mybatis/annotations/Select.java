package com.ashen.design.mybatis.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 查询的注解
 */
@Retention(RetentionPolicy.RUNTIME)  // 注解声明周期
@Target(ElementType.METHOD)  // 注解使用位置
public @interface Select {

    /**
     * Sql语句
     * @return
     */
    String value();
}
