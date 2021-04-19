package com.ashen.design.pattern.behavioral.state;

public class StopState extends VideoState {
    @Override
    public void play() {
        System.out.println("STOP   --->  PLAY");
        super.videoContext.setVideoState(VideoContext.PLAY_STATE);
    }

    @Override
    public void speed() {
        System.out.println("STOP   -/->  SPEED, 停止状态下不能快进");
    }

    @Override
    public void pause() {
        System.out.println("STOP   -/->  PAUSE, 停止状态不能暂停");
    }

    @Override
    public void stop() {
        System.out.println("STOP   -/->  STOP, 已经处于停止状态");
    }
}
