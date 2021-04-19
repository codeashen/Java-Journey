package com.ashen.design.pattern.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程目录类，其中包含课程
 */
public class CourseCatalog extends CatalogComponent {

    // 课程类中组合了课程集合
    private List<CatalogComponent> items = new ArrayList<>();
    // 目录名称
    private String name;
    // 目录层级
    private Integer level;

    public CourseCatalog(String name, Integer level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public void add(CatalogComponent cateLogComponent) {
        items.add(cateLogComponent);
    }

    @Override
    public void remove(CatalogComponent cateLogComponent) {
        items.remove(cateLogComponent);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void print() {
        System.out.println(this.name);
        for (CatalogComponent item : items) {
            // 根据目录层级打印缩进个数
            if (this.level != null) {
                for (int i = 0; i < this.level; i++) {
                    System.out.print("\t");
                }
            }
            item.print();
        }
    }
}
