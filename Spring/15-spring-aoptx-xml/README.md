# 模块内容

**AOP方式实现事务控制——XML配置**

代码由 09-spring-account 复制改造而来

之前模块使用FactoryBean获取Service的代理对象实现事务控制；

本模块使用Spring的AOP实现事务控制

# 关注代码

1. bean.xml中对AOP的配置，通知类就是txManager，原来就有了，配置切入点和切面就好了