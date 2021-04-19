package com.ashen.design.pattern.behavioral.state;

import lombok.Getter;

/**
 * 视频上下文
 */
public class VideoContext {
    @Getter
    private VideoState videoState;

    public static final PlayState PLAY_STATE = new PlayState();
    public static final SpeedState SPEED_STATE = new SpeedState();
    public static final PauseState PAUSE_STATE = new PauseState();
    public static final StopState STOP_STATE = new StopState();

    public void setVideoState(VideoState videoState) {
        this.videoState = videoState;
        this.videoState.setVideoContext(this);
    }

    public void play() {
        this.videoState.play();
    }

    public void speed() {
        this.videoState.speed();
    }

    public void pause() {
        this.videoState.pause();
    }

    public void stop() {
        this.videoState.stop();
    }
}
