package com.ashen.design.pattern.behavioral.visitor;

/**
 * 访问者实现类，可以扩展不同的访问者，实现不同的访问方法
 */
public class Visitor implements IVisitor {
    // 访问免费课程
    @Override
    public void visit(FreeCourse freeCourse) {
        System.out.println("免费课程: " + freeCourse.getName());
    }

    // 访问实战课程
    @Override
    public void visit(CodingCourse codingCourse) {
        System.out.println("付费课程: " + codingCourse.getName() + ", 价格: " + codingCourse.getPrice());
    }
}
