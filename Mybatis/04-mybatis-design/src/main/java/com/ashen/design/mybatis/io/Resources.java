package com.ashen.design.mybatis.io;

import java.io.InputStream;

/**
 * 资源工具类，用于读取配置文件
 */
public class Resources {

    /**
     * 使用类加载器读取配置文件
     * 根据传入的参数，获取一个字节输入流
     * @param filePath 配置文件
     * @return 配置文件的字节输入流
     */
    public static InputStream getResourceAsStream(String filePath) {
        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }
}
