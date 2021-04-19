package com.ashen.design.pattern.behavioral.interpreter;

public class OperatorUtil {
    // 是否是运算符
    public static boolean isOperator(String symbol) {
        return (symbol.equals("+") || (symbol.equals("*")));
    }

    // 返回相应解释器
    public static Interpreter getExpressionObject(Interpreter firstExpression, Interpreter secondExpression, String symbol) {
        if ("+".equals(symbol)) {
            return new AddInterpreter(firstExpression, secondExpression);
        } else if ("*".equals(symbol)) {
            return new MultiInterpreter(firstExpression, secondExpression);
        }
        return null;
    }
}
