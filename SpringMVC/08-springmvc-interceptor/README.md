# 模块内容

SpringMVC拦截器

1. 实现HandlerInterceptor接口，接口中没有抽象方法，有三个默认方法，提供了方法体

2. 拦截器对象要在SpringMVC配置文件中配置，见springmvc.xml
可以在preHandle 或postHandle 中使用请求、响应对象，转发或重定向等

# 关注代码

1. 拦截器MyInterception1中的三个方法用法

2. springmvc.xml中配置拦截器链的方法

3. 注意拦截器的执行顺序

本例中方法执行顺序
~~~
MyInterceptor1执行了...前1111
MyInterceptor2执行了...前2222
controller方法执行了...
MyInterceptor2执行了...后2222
MyInterceptor1执行了...后1111
success.jsp执行了...
MyInterceptor2执行了...最后2222
MyInterceptor1执行了...最后1111
~~~