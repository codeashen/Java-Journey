package com.ashen.design.pattern.behavioral.memento;

public class Test {
    public static void main(String[] args) {
        ArticleMementoManager articleMementoManager = new ArticleMementoManager();
        Article article = new Article("文档标题A", "文档内容A", "文档图片A");
        ArticleMemento articleMemento = article.saveToMemento();
        articleMementoManager.addMemento(articleMemento);
        System.out.println("init status: " + article);

        System.out.println("====== modify to version B ======");
        article.setTitle("文档标题B");
        article.setContent("文档内容B");
        article.setImgs("文档图标B");
        System.out.println("version B status: " + article);
        articleMemento = article.saveToMemento();
        articleMementoManager.addMemento(articleMemento);

        System.out.println("====== modify to version C ======");
        article.setTitle("文档标题C");
        article.setContent("文档内容C");
        article.setImgs("文档图标C");
        System.out.println("version C status: " + article);

        System.out.println("====== revert 1 ======");
        articleMemento = articleMementoManager.getMemento();
        article.updateFromMemento(articleMemento);
        System.out.println("revert 1 status: " + article);

        System.out.println("====== revert 2 ======");
        articleMemento = articleMementoManager.getMemento();
        article.updateFromMemento(articleMemento);
        System.out.println("revert 2 status: " + article);
    }
}
