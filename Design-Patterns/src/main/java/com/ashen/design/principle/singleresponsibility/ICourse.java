package com.ashen.design.principle.singleresponsibility;

public interface ICourse {
    String getCourseName();

    byte[] getCourseVideo();

    void studyCourse();

    void refundCourse();
}
