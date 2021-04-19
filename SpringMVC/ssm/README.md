# 模块内容

【SSM整合】整合之前，先将三个框架各自搭建好

## 一、Spring整合SpringMVC

1. 启动服务器前端控制器只加载了springmvc.xml，而Spring配置文件applicationContext.xml从头到尾没有加载。
所以利用SpringMVC提供的监听器ContextLoaderListener加载Spring配置文件，该监听器监听了ServletContext域对象。

2. 加载了Spring配置文件，就能创建service、dao的Bean，就可以将service注入controller了

## 二、Spring整合Mybatis

1. 整合就是将Mybatis主配置文件中的内容写到Spring配置文件中。所以SqlMapConfig.xml就放到test的资源目录下了，正式环境不需要。

# 关注代码

## 一、Spring整合SpringMVC

1. web.xml中监听器ContextLoaderListener的配置

## 二、Spring整合Mybatis

1. applicationContext.xml中整合Mybatis的配置

## 三、Spring声明式事务配置

1. 不属于整合的部分了，在applicationContext.xml中配置，关注一下