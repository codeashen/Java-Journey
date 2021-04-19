# 模块内容

Mybatis注解配置小案例

## 一、mybatis基于注解的入门案例

    把IUserDao.xml移除，在dao接口的方法上使用@Select注解，并且指定SQL语句
    同时需要在SqlMapConfig.xml中的mapper配置时，使用class属性指定dao接口的全限定类名。

# 关注代码

1. 删除的映射文件IUserMapper.xml，注解配置不需要该文件

2. IUserDao接口方法上的注解