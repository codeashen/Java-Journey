# 模块内容

SpringMVC的参数绑定

# 关注代码

1. 几种参数绑定形式都在 index.jsp 和 ParamController 中演示了

2. 自定义类型转换器，是 utils 包中的 StringToDateConverter

3. 自定义类型转换器的配置在 springmvc.xml 中，有两处

4. web.xml中配置了SpringMVC提供的编码过滤器，解决中文乱码问题