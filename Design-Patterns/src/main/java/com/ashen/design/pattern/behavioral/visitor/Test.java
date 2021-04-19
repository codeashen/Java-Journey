package com.ashen.design.pattern.behavioral.visitor;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Course> courseList = new ArrayList<>();
        FreeCourse freeCourse = new FreeCourse();
        freeCourse.setName("SpringMVC数据绑定");
        CodingCourse codingCourse = new CodingCourse();
        codingCourse.setName("设计模式");
        codingCourse.setPrice(100);
        courseList.add(freeCourse);
        courseList.add(codingCourse);

        for (Course course : courseList) {
            course.accept(new Visitor());
        }
    }
}
