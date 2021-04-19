package com.ashen.authority.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 密码加密工具类
 */
public class PasswordEncodingUtils {

    /**
     * 使用BCryptPasswordEncoder类加密
     */
    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * 获取加密后的密码字符串
     * @param password
     * @return
     */
    public static String encodePassword(String password) {
        return encoder.encode(password);
    }
}
