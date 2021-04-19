package com.ashen.design.pattern.behavioral.observer;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Observable;

/**
 * 被观察的对象
 */
@Data
@AllArgsConstructor
public class Course extends Observable {
    private String courseName;

    public void produceQuestion(Course course, Question question) {
        System.out.println(question.getUserName() + "在" + course.getCourseName() + "提交了一个问题");
        // 设置状态改变，通知观察者
        setChanged();
        notifyObservers(question);
    }
}
