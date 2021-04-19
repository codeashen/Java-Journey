package com.ashen.design.pattern.behavioral.chainofreresponsibility;

public class Test {
    public static void main(String[] args) {
        ArticleApprover articleApprover = new ArticleApprover();
        VideoApprover videoApprover = new VideoApprover();

        Course course = new Course();
        course.setName("Spring入门到精通");
        course.setArticle("Spring手记");
        course.setVideo("Spring视频");

        articleApprover.setNextApprover(videoApprover);
        articleApprover.deploy(course);
    }
}
