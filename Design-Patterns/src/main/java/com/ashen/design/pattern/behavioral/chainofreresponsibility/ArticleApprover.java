package com.ashen.design.pattern.behavioral.chainofreresponsibility;

import org.springframework.util.StringUtils;

/**
 * 手记审查者
 */
public class ArticleApprover extends Approver {
    @Override
    public void deploy(Course course) {
        System.out.println("开始手记审查");
        if (!StringUtils.isEmpty(course.getArticle())) {
            System.out.println(course.getName() + " 含有手记,批准");
            if (approver != null) {
                approver.deploy(course);
            } else {
                System.out.println("审批流程结束,审查通过");
            }
        } else {
            System.out.println(course.getName() + " 缺少手记,不批准,流程结束.");
        }
    }
}
