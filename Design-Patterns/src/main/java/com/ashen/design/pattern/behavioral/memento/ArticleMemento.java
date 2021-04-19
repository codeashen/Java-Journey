package com.ashen.design.pattern.behavioral.memento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 文档快照类，不需要setter方法，快照属性不可变
 */
@Getter
@ToString
@AllArgsConstructor
public class ArticleMemento {
    private String title;
    private String content;
    private String imgs;
}
