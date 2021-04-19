package com.ashen.design.pattern.structural.flyweight;

public class Test {
    // 部门
    private static final String departments[] = {"RD","QA","PM","BD"};

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String department = departments[(int) (Math.random() * departments.length)];
            Manager manager = (Manager) EmployeeHolder.getManager(department);
            manager.report();
        }
    }
}
