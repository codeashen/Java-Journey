package com.ashen.design.mybatis.cfg;

/**
 * 用于封装 SQL语句 和 resultType的全类名
 * 对应Mapper配置文件中的一个sql配置
 */
public class Mapper {

    private String queryString;  // sql
    private String resultType;   // 查询结果全类名

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
