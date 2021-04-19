package com.ashen.design.principle.dependenceinversion;

public class PythonCourse implements ICourse {
    @Override
    public void studyCourse() {
        System.out.println("Lucifer在学习Python课程");
    }
}
