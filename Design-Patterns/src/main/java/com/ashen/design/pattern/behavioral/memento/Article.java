package com.ashen.design.pattern.behavioral.memento;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 文档类
 */
@Data
@AllArgsConstructor
public class Article {
    private String title;
    private String content;
    private String imgs;

    // 保存到快照方法
    public ArticleMemento saveToMemento() {
        return new ArticleMemento(this.title, this.content, this.imgs);
    }

    // 从快照恢复方法
    public void updateFromMemento(ArticleMemento articleMemento) {
        this.title = articleMemento.getTitle();
        this.content = articleMemento.getContent();
        this.imgs = articleMemento.getImgs();
    }
}
