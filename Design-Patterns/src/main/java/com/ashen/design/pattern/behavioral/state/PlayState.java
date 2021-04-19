package com.ashen.design.pattern.behavioral.state;

public class PlayState extends VideoState {
    @Override
    public void play() {
        System.out.println("PLAY   -/->  PLAY, 已经处于播放状态");
    }

    @Override
    public void speed() {
        System.out.println("PLAY   --->  SPEED");
        super.videoContext.setVideoState(VideoContext.SPEED_STATE);
    }

    @Override
    public void pause() {
        System.out.println("PLAY   --->  PAUSE");
        super.videoContext.setVideoState(VideoContext.PAUSE_STATE);
    }

    @Override
    public void stop() {
        System.out.println("PLAY   --->  STOP");
        super.videoContext.setVideoState(VideoContext.STOP_STATE);
    }
}
