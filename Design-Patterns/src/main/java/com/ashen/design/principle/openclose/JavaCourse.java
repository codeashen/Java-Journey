package com.ashen.design.principle.openclose;

import lombok.AllArgsConstructor;

/**
 * Java课程
 */
@AllArgsConstructor
public class JavaCourse implements ICourse {
    private Integer id;
    private String name;
    private Double price;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }
}
