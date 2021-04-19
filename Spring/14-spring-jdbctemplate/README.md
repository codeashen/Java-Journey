# 模块内容

JdbcTemplate的基本使用

# 关注代码

test中每个demo对应不同的点

1. demo1：JdbcTemplate的基本使用

2. demo2：JdbcTemplate的CRUD操作，重点关注RowMapper封装结果集的策略
    * 自定义RowMapper和内置实现类BeanPropertyRowMapper

3. demo3：JdbcTemplate在Dao中的使用，了解JdbcDaoSupport的使用
    * dao的两个实现类，分别展示了是否继承JdbcDaoSupport的效果，继承可以减少重复代码
    * JdbcDaoSupportDesign是自定义的JdbcDaoSupport，展示了其原理
    * 对用两个dao在bean.xml配置也有所差异，需要注入的不同
    
4. 最后可以看一下bean.xml末尾，Spring对配置文件的引入方式

