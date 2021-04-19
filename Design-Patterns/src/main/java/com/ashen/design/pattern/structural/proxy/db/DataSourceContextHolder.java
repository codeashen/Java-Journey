package com.ashen.design.pattern.structural.proxy.db;

/**
 * 本线程中使用数据库的ThreadLocal持有类
 */
public class DataSourceContextHolder {

    // 存本线程中使用的数据库
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setDBType(String dbType) {
        CONTEXT_HOLDER.set(dbType);
        System.out.println("ThreadLocal中数据源key设置为" + getDBType());
    }

    public static String getDBType() {
        return CONTEXT_HOLDER.get();
    }

    public static void clearDBType() {
        CONTEXT_HOLDER.remove();
    }
}
