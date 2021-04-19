package com.ashen.design.pattern.structural.composite;

/**
 * 目录组件抽象类，可以是目录，或者目录下的课程
 */
public abstract class CatalogComponent {

    public void add(CatalogComponent cateLogComponent) {
        throw new UnsupportedOperationException("不支持添加操作");
    }

    public void remove(CatalogComponent cateLogComponent) {
        throw new UnsupportedOperationException("不支持删除操作");
    }

    public String getName() {
        throw new UnsupportedOperationException("不支持获取名称操作");
    }

    public double getPrice() {
        throw new UnsupportedOperationException("不支持后去价格操作");
    }

    public void print() {
        throw new UnsupportedOperationException("不支持打印操作");
    }
}
