package com.ashen.design.pattern.behavioral.templatemethod;

public class JavaCourse extends ACourse {
    @Override
    void packageCourse() {
        System.out.println("提供Java课程源代码");
    }

    @Override
    protected boolean needWriteArticle() {
        return true;
    }
}
