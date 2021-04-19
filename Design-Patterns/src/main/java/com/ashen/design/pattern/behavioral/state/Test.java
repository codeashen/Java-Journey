package com.ashen.design.pattern.behavioral.state;

public class Test {
    public static void main(String[] args) {
        VideoContext videoContext = new VideoContext();
        videoContext.setVideoState(new StopState());
        System.out.println("当前状态: " + videoContext.getVideoState().getClass().getSimpleName());

        videoContext.play();
        System.out.println("当前状态: " + videoContext.getVideoState().getClass().getSimpleName());

        videoContext.pause();
        System.out.println("当前状态: " + videoContext.getVideoState().getClass().getSimpleName());

        videoContext.speed();
        System.out.println("当前状态: " + videoContext.getVideoState().getClass().getSimpleName());

        videoContext.stop();
        System.out.println("当前状态: " + videoContext.getVideoState().getClass().getSimpleName());

        videoContext.speed();
        System.out.println("当前状态: " + videoContext.getVideoState().getClass().getSimpleName());
    }
}
