package com.ashen.design.pattern.behavioral.observer;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者
 */
@Data
@AllArgsConstructor
public class Teacher implements Observer {
    private String teacherName;

    /**
     * 观察者收到通知时，调用的方法
     * @param o   可观察到的对象
     * @param arg 传递给notifyObservers方法的参数
     */
    @Override
    public void update(Observable o, Object arg) {
        Course course = (Course) o;
        Question question = (Question) arg;
        System.out.println(teacherName + "收到一个" + question.getUserName() + "的问题, 来自课程《"
                + course.getCourseName() + "》, 问题内容: " + question.getQuestionContent());
    }
}
