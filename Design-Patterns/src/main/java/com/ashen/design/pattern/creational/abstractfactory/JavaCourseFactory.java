package com.ashen.design.pattern.creational.abstractfactory;

/**
 * Java课程工厂，给出了Java产品族中所有产品的获取方法
 * 从此工厂里得到了视频一定是Java视频，得到的手记一定的Java手记
 */
public class JavaCourseFactory implements CourseFactory {
    @Override
    public Video getVideo() {
        return new JavaVideo();
    }

    @Override
    public Article getArticle() {
        return new JavaArticle();
    }
}
