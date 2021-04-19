package com.ashen.design.pattern.behavioral.chainofreresponsibility;

/**
 * 审查者
 */
public abstract class Approver {
    protected Approver approver;

    // 设置下一个审查者
    public void setNextApprover(Approver approver) {
        this.approver = approver;
    }

    // 发布课程方法
    public abstract void deploy(Course course);
}
