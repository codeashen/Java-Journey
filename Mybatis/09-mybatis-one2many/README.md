# 模块内容

Mybatis的多表查询，一对一 和 一对多

## 一、一对一（多对一）

### 1、概念

Mybatis把【多对一】区别于【一对多】，把多对一看做是一对一

    示例：【一对一】   一个Account属于一个User

    解释：
        此关系中，应该是  账户---用户   n---1
        Mybatis把【多对一】看作是一对一，一个Account属于一个User

### 2、用法

1. 在从表实体中定义主表实体的对象引用，即把User作为Account的属性

2. 从表映射文件中resultMap，其中用association标签定义主表对象映射

## 二、一对多

### 用法

1. 在主表实体中定义从表实体的集合引用

2. 主表映射文件配置resultMap，其中用collection标签定义从表集合映射

# 关注代码

一对一部分：

1. Account类中user属性
2. IAccountDao.xml中的resultMap配置

一对多部分：

1. User类中accounts属性
2. IUserDao.xml中的resultMap配置


