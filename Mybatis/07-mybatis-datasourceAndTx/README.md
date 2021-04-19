# 模块内容

Mybatis连接池的配置和事务的管理

## 一、Mybatis连接池配置

### 1、连接池：

我们在实际开发中都会使用连接池。
因为它可以减少我们获取连接所消耗的时间。
	
### 2、mybatis中的连接池

mybatis连接池提供了3种方式的配置：

* 配置的位置：

    主配置文件SqlMapConfig.xml中的dataSource标签，type属性就是表示采用何种连接池方式。
    
* type属性的取值：

    1. POOLED    
        采用传统的javax.sql.DataSource规范中的连接池，mybatis中有针对规范的实现
    
    2. UNPOOLED  
        采用传统的获取连接的方式，虽然也实现Javax.sql.DataSource接口，但是并没有使用池的思想。
    
    3. JNDI      
        采用服务器提供的JNDI技术实现，来获取DataSource对象，不同的服务器所能拿到DataSource是不一样。
        
        注意：如果不是web或者maven的war工程，是不能使用的。
        我们课程中使用的是tomcat服务器，采用连接池就是dbcp连接池。

## 二、Mybatis事务管理

创建SqlSession对象时，通过参数设置事务是否自动提交

1. 手动提交

    `Sqlsession session = sqlSessionFactory.openSession()`

2. 自动提交

    `SqlSession session = sqlSessionFactory.openSession(true)`

# 关注代码

1. 主配置文件SqlMapConfig.xml中环境配置的连接池部分

2. 测试类MybatisTest，获取SqlSession方式