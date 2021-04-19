package com.ashen.design.pattern.creational.builder;

public class Test {
    public static void main(String[] args) {
        CourseActualBuilder builder = new CourseActualBuilder();
        builder.buildCourseName("Java设计模式");
        builder.buildCoursePPT("课程PPT");
        builder.buildCourseVideo("课程视频");
        builder.buildCourseArticle("课程手记");
        builder.buildCourseQA("课程问答");
        Course course = builder.build();

        System.out.println(course);
    }
}
