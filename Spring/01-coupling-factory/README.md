# 模块内容

1. jdbc包：传统jdbc连接分析耦合

2. decoupling包：工厂模式解耦

    1. 第一版：多例的，影响性能
    
    2. 第二版：单例的
    
# 关注代码

1. jdbc包中关注JdbcDemo中的注释

2. decoupling包模拟了三层架构的耦合与解耦
    
    使用工厂模式解耦，重点关注BeanFactory类