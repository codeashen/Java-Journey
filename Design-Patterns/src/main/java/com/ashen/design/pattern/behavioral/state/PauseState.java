package com.ashen.design.pattern.behavioral.state;

public class PauseState extends VideoState {
    @Override
    public void play() {
        System.out.println("PAUSE  --->  PLAY");
        super.videoContext.setVideoState(VideoContext.PLAY_STATE);
    }

    @Override
    public void speed() {
        System.out.println("PAUSE  --->  SPEED");
        super.videoContext.setVideoState(VideoContext.SPEED_STATE);
    }

    @Override
    public void pause() {
        System.out.println("PAUSE  -/->  PAUSE, 已经处于暂停状态");
    }

    @Override
    public void stop() {
        System.out.println("PAUSE  --->  STOP");
        super.videoContext.setVideoState(VideoContext.STOP_STATE);
    }
}
