# 模块内容

了解内容，SpringMVC拦截器改变http请求方式，实现restful风格

restful风格：使用同一请求路径，不同的请求方式区别请求，后台实现不同逻辑

# 关注代码

## 拦截器使用方式

~~~
第一步：在web.xml 中配置该过滤器。
第二步：请求方式必须使用post 请求。
第三步：按照要求提供_method 请求参数，该参数的取值就是我们需要的请求方式。
~~~

## 代码示例

1. web.xml中对HiddenHttpMethodFilter的配置

2. index.jsp中，四个表单中隐藏域参数_method指定拦截器修改的请求方式

3. HttpMethodController中每个方法的RequestMapping注解配置