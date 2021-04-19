package com.ashen.design.pattern.creational.prototype;

import lombok.Data;

@Data
public class Mail implements Cloneable {
    private String name;
    private String emailAddress;
    private String content;

    public Mail() {
        System.out.println("Mail Class Constructor");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
