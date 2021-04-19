package com.ashen.design.pattern.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

public class CourseCollectionImpl implements CourseCollection {

    private List<Course> courseList;

    public CourseCollectionImpl() {
        this.courseList = new ArrayList<>();
    }

    @Override
    public void addCourse(Course course) {
        courseList.add(course);
    }

    @Override
    public void removeCourse(Course course) {
        courseList.remove(course);
    }

    @Override
    public CourseIterator getIterator() {
        return new CourseIteratorImpl(courseList);
    }
}
