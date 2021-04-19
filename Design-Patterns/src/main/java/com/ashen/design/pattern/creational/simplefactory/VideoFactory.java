package com.ashen.design.pattern.creational.simplefactory;

public class VideoFactory {

    public Video getVideo(String type) {
        if ("java".equalsIgnoreCase(type)) {
            return new JavaVideo();
        } else if ("python".equalsIgnoreCase(type)) {
            return new PythonVideo();
        }
        return null;
    }

    public Video getVideo(Class c) {
        Video video = null;
        try {
            video = (Video) c.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return video;
    }

}
