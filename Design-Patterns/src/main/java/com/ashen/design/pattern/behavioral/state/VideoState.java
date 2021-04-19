package com.ashen.design.pattern.behavioral.state;

import lombok.Setter;

/**
 * 视频播放状态
 */
public abstract class VideoState {
    @Setter
    protected VideoContext videoContext;

    // 播放操作
    public abstract void play();
    // 快进操作
    public abstract void speed();
    // 暂停操作
    public abstract void pause();
    // 停止操作
    public abstract void stop();
}
