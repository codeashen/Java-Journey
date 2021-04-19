package com.ashen.design.pattern.behavioral.visitor;

import lombok.Data;

/**
 * 实战课程
 */
@Data
public class CodingCourse extends Course {
    private int price;

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}
