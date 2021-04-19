package com.ashen.design.pattern.behavioral.memento;

import java.util.Stack;

/**
 * 快照管理器，使用栈保存快照，保证执行撤销操作时，恢复的是最新的状态
 */
public class ArticleMementoManager {

    private final Stack<ArticleMemento> ARTICLE_MEMENTO_STACK = new Stack<>();

    public ArticleMemento getMemento() {
        return ARTICLE_MEMENTO_STACK.pop();
    }

    public void addMemento(ArticleMemento articleMemento) {
        ARTICLE_MEMENTO_STACK.push(articleMemento);
    }
}
