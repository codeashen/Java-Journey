# 模块内容

Mybatis的一对一、一对多的延迟加载策略

## 实现方式

1. 主配置文件SqlMapConfig.xml中，settings标签内开启延迟加载

2. 映射文件中sql不再用多表连接的sql

3. 映射文件中resultMap的association或collection标签加入column和select属性

# 关注代码

1. SqlMapConfig.xml

2. 映射文件

3. dao中方法

4. 测试方法看看，不遍历就延迟加载
