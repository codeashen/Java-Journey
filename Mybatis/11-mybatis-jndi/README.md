# 模块内容

Mybatis主配置中使用JNDI方式配置数据源

# 关注代码

1. META-INF中的context.xml，JNDI数据源配置

2. SqlMapConfig.xml中配置数据源方式

3. 不用测试类，因为不经过服务器。用经过服务器的index.jsp测试代码