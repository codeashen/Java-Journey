package com.ashen.design.pattern.behavioral.interpreter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NumberInterpreter implements Interpreter {
    private final int number;

    public NumberInterpreter(String number) {
        this.number = Integer.parseInt(number);
    }

    @Override
    public int interpret() {
        return this.number;
    }
}
