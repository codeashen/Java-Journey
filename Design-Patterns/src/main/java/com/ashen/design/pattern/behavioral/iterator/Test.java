package com.ashen.design.pattern.behavioral.iterator;

public class Test {
    public static void main(String[] args) {
        Course course1 = new Course("Java电商一期");
        Course course2 = new Course("Java电商二期");
        Course course3 = new Course("Java设计模式精讲");
        Course course4 = new Course("Python课程");
        Course course5 = new Course("算法课程");
        Course course6 = new Course("前端课程");


        CourseCollection courseCollection = new CourseCollectionImpl();

        courseCollection.addCourse(course1);
        courseCollection.addCourse(course2);
        courseCollection.addCourse(course3);
        courseCollection.addCourse(course4);
        courseCollection.addCourse(course5);
        courseCollection.addCourse(course6);

        printCourses(courseCollection);

        System.out.println("-----------------------------------------");

        courseCollection.removeCourse(course4);
        courseCollection.removeCourse(course5);

        printCourses(courseCollection);
    }

    public static void printCourses(CourseCollection courseCollection) {
        CourseIterator courseIterator = courseCollection.getIterator();
        while (!courseIterator.isLastCourse()) {
            Course course = courseIterator.nextCourse();
            System.out.println(course.getName());
        }
    }
}
