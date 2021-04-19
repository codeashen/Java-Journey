package com.ashen.design.principle.dependenceinversion;

public class Lucifer {

    public void setICourse(ICourse iCourse) {
        this.iCourse = iCourse;
    }

    private ICourse iCourse;

    public void studyItCourse() {
        iCourse.studyCourse();
    }

}
