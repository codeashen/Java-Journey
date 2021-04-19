package com.ashen.design.pattern.behavioral.command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CourseVideo {
    private String name;

    public void open() {
        System.out.println(this.name + "课程视频开放");
    }

    public void close() {
        System.out.println(this.name + "课程视频关闭");
    }
}
