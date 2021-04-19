# 模块内容

Mybatis环境的搭建和快速入门 

## 一、Mybatis环境搭建

1. 创建maven工程并导入坐标
2. 创建实体类和dao的接口
3. 创建Mybatis的主配置文件
        SqlMapConifg.xml
4. 创建映射配置文件
        IUserDao.xml
            
环境搭建的注意事项：

    1. 创建IUserDao.xml 和 IUserDao.java时名称是为了和我们之前的知识保持一致。
       在Mybatis中它把持久层的操作接口名称和映射文件也叫做：Mapper
       所以：IUserDao 和 IUserMapper是一样的
       
    2. 在idea中创建目录的时候，它和包是不一样的
       包在创建时：com.itheima.dao它是三级结构
       目录在创建时：com.itheima.dao是一级目录
       
    3. mybatis的映射配置文件位置必须和dao接口的包结构相同
    
    4. 映射配置文件的mapper标签namespace属性的取值必须是dao接口的全限定类名
    
    5. 映射配置文件的操作配置（select），id属性的取值必须是dao接口的方法名

    当我们遵从了第3、4、5点之后，我们在开发中就**无须再写dao的实现类**。

##	二、mybatis快速入门

    第一步：读取配置文件
    第二步：创建SqlSessionFactory工厂
    第三步：创建SqlSession
    第四步：创建Dao接口的代理对象
    第五步：执行dao中的方法
    第六步：释放资源

    注意事项：
        不要忘记在映射配置中告知mybatis要封装到哪个实体类中
        配置的方式：指定实体类的全限定类名
		
	明确：
		我们在实际开发中，都是越简便越好，所以都是采用不写dao实现类的方式。
		不管使用XML还是注解配置。
		但是Mybatis它是支持写dao实现类的。

# 关注代码

1. Mybatis主配置文件SqlMapConfig.xml中各配置的含义

2. Mybatis映射文件IUserMapper.xml中Mapper标签的namespace属性

3. 测试类MybatisTest