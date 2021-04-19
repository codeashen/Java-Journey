package com.ashen.design.principle.dependenceinversion;

public class FECourse implements ICourse {
    @Override
    public void studyCourse() {
        System.out.println("Lucifer在学习FE课程");
    }
}
