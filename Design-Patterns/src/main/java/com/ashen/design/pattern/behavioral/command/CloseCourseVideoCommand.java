package com.ashen.design.pattern.behavioral.command;

import lombok.AllArgsConstructor;

/**
 * 命令2，关闭视频命令
 */
@AllArgsConstructor
public class CloseCourseVideoCommand implements Command {

    private CourseVideo courseVideo;

    @Override
    public void execute() {
        this.courseVideo.close();
    }
}
