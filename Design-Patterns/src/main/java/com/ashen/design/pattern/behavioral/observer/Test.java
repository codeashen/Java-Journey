package com.ashen.design.pattern.behavioral.observer;

public class Test {
    public static void main(String[] args) {
        // 创建可观察的对象
        Course course = new Course("相对论");
        // 创建观察者
        Teacher teacher1 = new Teacher("Alpha");
        Teacher teacher2 = new Teacher("Beta");

        // 注册观察者
        course.addObserver(teacher1);
        course.addObserver(teacher2);
        // 业务逻辑代码，改变被观察对象状态，通知观察者
        Question question = new Question("Lucifer", "相对论公式是什么");
        course.produceQuestion(course, question);
    }
}
