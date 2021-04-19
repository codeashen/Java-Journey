package com.ashen.jdbctemplate.test;

public class TestIncr {
    public static void main(String args[]) {
        int i = 0;
        i = i++ + i;
        System.out.println("I =" +i);
    }
}
