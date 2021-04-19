package com.ashen.design.pattern.creational.builder.v2;

import com.ashen.design.pattern.creational.builder.Course;

public class CourseBuilder {
    // 内部初始化课程
    private final Course course = new Course();
    
    public CourseBuilder buildCourseName(String courseName) {
        course.setCourseName(courseName);
        return this;
    }
    
    public CourseBuilder buildCoursePPT(String coursePPT) {
        course.setCoursePPT(coursePPT);
        return this;
    }

    public CourseBuilder buildCourseVideo(String courseVideo) {
        course.setCourseVideo(courseVideo);
        return this;
    }

    public CourseBuilder buildCourseArticle(String courseArticle) {
        course.setCourseArticle(courseArticle);
        return this;
    }
    
    public CourseBuilder buildCourseQA(String courseQA) {
        course.setCourseQA(courseQA);
        return this;
    }

    public Course build() {
        return course;
    }
}
