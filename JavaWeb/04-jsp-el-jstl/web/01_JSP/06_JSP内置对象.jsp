<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP内置对象</title>
</head>
<body>
查看源码，见注释内容

<!--

名称                 真实类型                 作用
pageContext         pageContext             域对象，当前页面范围；获取其他8个内置对象
request             HttpServletRequest      域对象，request范围
session             HttpSession             域对象，session范围
application         ServletContext          域对象，application范围，所有客户端间共享数据
response            HttpServletResponse     响应对象
out                 JspWriter               输出流对象
page                Object                  当前页面(Servlet)对象，Servlet中的this
config              ServletConfig           Servlet配置对象
exception           Throwable               异常对象

-->

</body>
</html>
