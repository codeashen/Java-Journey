# 模块内容

SpringMVC异常处理

配置了异常处理器后，controller方法抛出异常，前端控制器回去找异常处理器，异常处理器返回ModelAndView，经过视图解析器返回页面

# 关注代码

1. 编写异常处理器SysExceptionResolver，实现HandlerExceptionResolver接口

2. springmvc.xml中配置异常处理器bean