package com.ashen.design.pattern.behavioral.interpreter;

public class Test {
    // 解释器模式很少用
    public static void main(String[] args) {
        String inputStr = "6 100 11 + *";
        LuciferExpressionParser expressionParser = new LuciferExpressionParser();
        int result = expressionParser.parse(inputStr);
        System.out.println("解释器计算结果: " + result);
    }
}
