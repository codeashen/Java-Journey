package com.ashen.design.pattern.behavioral.state;

public class SpeedState extends VideoState {
    @Override
    public void play() {
        System.out.println("SPEED  --->  PLAY");
        super.videoContext.setVideoState(VideoContext.PLAY_STATE);
    }

    @Override
    public void speed() {
        System.out.println("SPEED  -/->  SPEED, 已经处于快进状态");
    }

    @Override
    public void pause() {
        System.out.println("SPEED  --->  PAUSE");
        super.videoContext.setVideoState(VideoContext.PAUSE_STATE);
    }

    @Override
    public void stop() {
        System.out.println("SPEED  --->  STOP");
        super.videoContext.setVideoState(VideoContext.STOP_STATE);
    }
}
