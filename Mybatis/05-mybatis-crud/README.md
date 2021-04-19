# 模块内容

Mybatis的CRUD操作（不写实现类的方式）

# 关注代码 

1. SqlMapConfig.xml

    三个标签：properties、typeAliases、mappers子标签：package

2. 测试类MybatisTest

    Before和After注解；提交事务；testSave方法回写id；testFindByName方法模糊查询
    
3. 映射文件IUserDao.xml
    
    resultMap使用；selectKey使用；#{}和${}参数的区别