package com.ashen.design.pattern.behavioral.iterator;

public interface CourseCollection {
    void addCourse(Course course);
    void removeCourse(Course course);

    CourseIterator getIterator();
}
