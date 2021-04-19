package com.ashen.design.pattern.behavioral.observer;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 传递给观察者的参数
 */
@Data
@AllArgsConstructor
public class Question {
    private String userName;
    private String questionContent;
}
