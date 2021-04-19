package com.ashen.design.pattern.structural.composite;

import lombok.AllArgsConstructor;

/**
 * 课程类
 */
@AllArgsConstructor
public class Course extends CatalogComponent {
    private String name;
    private double price;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void print() {
        System.out.println("Course Name:" + name + ", Price:" +price);
    }
}
