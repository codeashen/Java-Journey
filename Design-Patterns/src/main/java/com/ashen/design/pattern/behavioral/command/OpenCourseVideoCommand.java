package com.ashen.design.pattern.behavioral.command;

import lombok.AllArgsConstructor;

/**
 * 命令1，开启视频命令
 */
@AllArgsConstructor
public class OpenCourseVideoCommand implements Command {

    private CourseVideo courseVideo;

    @Override
    public void execute() {
        this.courseVideo.open();
    }
}
