package com.ashen.jdbctemplate.test;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] people = str.split(";");
        for (String person : people) {
            String[] msg = person.split(",");
            System.out.printf("%-20s:   %02d\n", msg[0].trim(), Integer.parseInt(msg[1].trim()));
        }
    }
}