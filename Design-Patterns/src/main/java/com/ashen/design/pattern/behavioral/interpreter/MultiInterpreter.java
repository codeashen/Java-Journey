package com.ashen.design.pattern.behavioral.interpreter;

import lombok.AllArgsConstructor;

/**
 * 乘法解释器
 */
@AllArgsConstructor
public class MultiInterpreter implements Interpreter {

    private final Interpreter firstExpression;
    private final Interpreter secondExpression;

    @Override
    public int interpret() {
        return this.firstExpression.interpret() * this.secondExpression.interpret();
    }

    @Override
    public String toString() {
        return "*";
    }
}
