package com.ashen.design.pattern.creational.builder.v2;

import com.ashen.design.pattern.creational.builder.Course;

public class Test {
    public static void main(String[] args) {
        CourseBuilder builder = new CourseBuilder();
        Course course = builder.buildCourseName("Java设计模式").buildCoursePPT("课程PPT").buildCourseVideo("课程视频")
                .buildCourseArticle("课程手记").buildCourseQA("课程问答").build();

        System.out.println(course);
    }
}
