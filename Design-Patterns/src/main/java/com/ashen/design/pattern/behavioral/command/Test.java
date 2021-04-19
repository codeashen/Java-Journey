package com.ashen.design.pattern.behavioral.command;

/**
 * 后续可以扩展各种命令
 */
public class Test {
    public static void main(String[] args) {
        CourseVideo courseVideo = new CourseVideo("《Spring入门到精通》");
        OpenCourseVideoCommand openCourseVideoCommand = new OpenCourseVideoCommand(courseVideo);
        CloseCourseVideoCommand closeCourseVideoCommand = new CloseCourseVideoCommand(courseVideo);

        // 接收命令
        Staff staff = new Staff();
        staff.addCommand(openCourseVideoCommand);
        staff.addCommand(closeCourseVideoCommand);
        // 执行命令
        staff.executeCommands();
    }
}
