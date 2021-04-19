package com.ashen.design.pattern.creational.abstractfactory;

/**
 * 课程的抽象工厂，给出了同一产品族中的所有产品
 * 从特定工厂里去除的产品一定的属于同一产品族的
 */
public interface CourseFactory {
    // 获取课程视频
    Video getVideo();
    // 获取课程手记
    Article getArticle();
}
