package com.ashen.authority.utils;

import org.junit.Test;

public class PasswordEncodingUtilsTest {

    @Test
    public void encodePassword() {
        String s = PasswordEncodingUtils.encodePassword("123");
        // $2a$10$xJr4QS3iuzFOavAdwg3iX.uCqAmh/EYSb6btFuU3NRzanQj3ytAXu
        System.out.println(s);
    }
}