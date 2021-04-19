package com.ashen.design.pattern.structural.flyweight;

import lombok.Setter;

/**
 * 部门经理类
 * 外部状态: department,依赖于外不传入的参数
 * 内部状态: jobName,不随外部环境变化而改变
 */
public class Manager implements Employee {

    private String jobName = "部门经理";
    private String department;     // 领导部门
    @Setter
    private String reportContent;  // 报告内容

    public Manager(String department) {
        this.department = department;
    }

    @Override
    public void report() {
        System.out.println(reportContent);
    }
}