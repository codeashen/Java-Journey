package com.ashen.design.pattern.behavioral.templatemethod;

public class Test {
    public static void main(String[] args) {
        ACourse javaCourse = new JavaCourse();
        javaCourse.makeCourse();

        ACourse feCourse = new FECoure(true);
        feCourse.makeCourse();
    }
}
