package com.ashen.design.mybatis.cfg;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义Mybatis的配置类
 * 封装了数据库连接的4个配置和Mapper文件中的所有sql配置
 */
public class Configuration {

    private String driver;
    private String url;
    private String username;
    private String password;

    /**
     * 这个Map中，包含了Mybatis主配置中的mappers中的所有Mapper中的所有sql信息
     * String K：Mapper配置文件中sql名，其实是 namespace + "." + id
     *           对应dao接口中的方法名
     *           例：com.ashen.design.dao.IUserDao.findAll
     * Mapper V：封装了sql和resultType类型的对象
     */
    private Map<String, Mapper> mappers = new HashMap<>();

    public Map<String, Mapper> getMappers() {
        return mappers;
    }

    /**
     * 此mappers的set方法，需要使用追加的方式，而不能覆盖
     * @param mappers 需要追加的内容
     */
    public void setMappers(Map<String, Mapper> mappers) {
        this.mappers.putAll(mappers);
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
