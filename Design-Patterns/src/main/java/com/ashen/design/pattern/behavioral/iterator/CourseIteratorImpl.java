package com.ashen.design.pattern.behavioral.iterator;

import java.util.List;

public class CourseIteratorImpl implements CourseIterator {

    private List<Course> courseList;
    private int position;   // 初始为0
    private Course course;

    public CourseIteratorImpl(List<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public Course nextCourse() {
        System.out.println("返回课程位置是：" + position);  // 初始为0
        Course course = courseList.get(position);
        position ++;
        return course;
    }

    @Override
    public boolean isLastCourse() {
        return position >= courseList.size();
    }
}
