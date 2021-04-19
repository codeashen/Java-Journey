package com.ashen.design.pattern.behavioral.templatemethod;

public abstract class ACourse {

    // 模板方法
    protected final void makeCourse() {
        makePPT();
        makeVideo();
        if (needWriteArticle()) {
            makeArticle();
        }
        packageCourse();  // 上面都是固定的，这里需要子类实现
    }

    // 需要子类实现的部分
    abstract void packageCourse();

    // 钩子方法
    protected boolean needWriteArticle() {
        return false;
    }

    final void makePPT() {
        System.out.println("制作PPT");
    }
    final void makeVideo() {
        System.out.println("制作视频");
    }
    final void makeArticle() {
        System.out.println("编写手记");
    }
}
