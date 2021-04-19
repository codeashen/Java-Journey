package com.ashen.design.pattern.behavioral.chainofreresponsibility;

import org.springframework.util.StringUtils;

/**
 * 手记审查者
 */
public class VideoApprover extends Approver {
    @Override
    public void deploy(Course course) {
        System.out.println("开始视频审查");
        if (!StringUtils.isEmpty(course.getVideo())) {
            System.out.println(course.getName() + " 含有视频,批准");
            if (approver != null) {
                approver.deploy(course);
            } else {
                System.out.println("审批流程结束,审查通过");
            }
        } else {
            System.out.println(course.getName() + " 缺少视频,不批准,流程结束.");
        }
    }
}
