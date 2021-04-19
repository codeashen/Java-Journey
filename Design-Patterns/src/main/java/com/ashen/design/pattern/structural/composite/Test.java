package com.ashen.design.pattern.structural.composite;

public class Test {
    public static void main(String[] args) {
        CatalogComponent linuxCourse = new Course("Linux课程", 11);
        CatalogComponent osCourse = new Course("操作系统课程", 8);

        CatalogComponent javaCourseCatalog = new CourseCatalog("Java课程目录", 2);

        CatalogComponent springCourse = new Course("Spring课程", 10);
        CatalogComponent mybatisCourse = new Course("Mybatis课程", 10);
        CatalogComponent designPatternCourse = new Course("设计模式课程", 10);

        javaCourseCatalog.add(springCourse);
        javaCourseCatalog.add(mybatisCourse);
        javaCourseCatalog.add(designPatternCourse);

        CatalogComponent mainCatalog = new CourseCatalog("主目录", 1);

        mainCatalog.add(linuxCourse);
        mainCatalog.add(osCourse);
        mainCatalog.add(javaCourseCatalog);

        mainCatalog.print();
    }
}
