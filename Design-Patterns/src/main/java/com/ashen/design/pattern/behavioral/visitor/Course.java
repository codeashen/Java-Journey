package com.ashen.design.pattern.behavioral.visitor;

import lombok.Data;

/**
 * 课程抽象类
 */
@Data
public abstract class Course {
    private String name;

    // 参数上传入访问者对象，执行不同的访问方法
    public abstract void accept(IVisitor visitor);
}
