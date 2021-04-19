package com.ashen.design.pattern.behavioral.templatemethod;

public class FECoure extends ACourse {

    private boolean needWriteArticleFlag = false;

    public FECoure(boolean needWriteArticleFlag) {
        this.needWriteArticleFlag = needWriteArticleFlag;
    }

    @Override
    void packageCourse() {
        System.out.println("提供前端课程源代码");
        System.out.println("提供图片等素材文件");
    }

    @Override
    protected boolean needWriteArticle() {
        return this.needWriteArticleFlag;
    }

}
