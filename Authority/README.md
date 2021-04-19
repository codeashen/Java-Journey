# 关注点

1. 配置无逻辑跳转 [ViewConfig](authority-web/src/main/java/com/ashen/authority/config/ViewConfig.java)

2. Spring注解类型转换，DateTimeFormat注解，MVC参数绑定时直接按规则转换 [Product.departureTime](authority-domain/src/main/java/com/ashen/authority/domain/Product.java)

3. Mybatis多表查询 [OrderDao](authority-dao/src/main/java/com/ashen/authority/dao/OrderDao.java)

4. PageHelper分页查询 
    
    [配置PageHelper插件](authority-dao/src/main/resources/applicationContext-dao.xml)<br>
    PageHelper.startPage [开启分页](authority-service/src/main/java/com/ashen/authority/service/impl/OrderServiceImpl.java)<br>
    OrderController.findAll [PageInfo使用](authority-web/src/main/java/com/ashen/authority/controller/OrderController.java)
