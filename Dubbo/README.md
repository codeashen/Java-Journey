# Dubbo入门案例

模拟订单模块远程调用用户模块的场景，演示Dubbo的基本使用。

##  模块介绍

服务提供者：用户模块<br>
服务消费方：订单模块

| 模块                                                       | 内容                      |
| ---------------------------------------------------------- | ------------------------- |
| [mall-interface](mall-interface)                           | 实体类和服务接口所在模块  |
| [user-service-provider](user-service-provider)             | 用户模块                  |
| [order-service-consumer](order-service-consumer)           | 订单模块                  |
| [boot-user-service-provider](boot-user-service-provider)   | Spring Boot构建的用户模块 |
| [boot-order-service-consumer](boot-order-service-consumer) | Spring Boot构建的订单模块 |