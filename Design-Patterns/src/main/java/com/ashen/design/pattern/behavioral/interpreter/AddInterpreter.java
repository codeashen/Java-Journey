package com.ashen.design.pattern.behavioral.interpreter;

import lombok.AllArgsConstructor;

/**
 * 加法解释器
 */
@AllArgsConstructor
public class AddInterpreter implements Interpreter {

    private final Interpreter firstExpression;
    private final Interpreter secondExpression;

    @Override
    public int interpret() {
        return this.firstExpression.interpret() + this.secondExpression.interpret();
    }

    @Override
    public String toString() {
        return "+";
    }
}
