# 模块内容

通过转账操作，引出事务应该在业务层控制，
使用自定义工具类实现业务层的事务控制，
目的在于引出动态代理和AOP

# 关注代码

1. 先看utils包中的ConnectionUtils和TransactionManager
    * 理解TreadLocal的使用
    
2. AccountServiceImpl
    * 注入TransactionManager，实现事务控制

3. AccountDaoImpl
    * 注入的QueryRunner中不再有Connection
    * 注入ConnectionUtils提供Connection

4. 理解了上面就行了，bean.xml稍微看下就好了