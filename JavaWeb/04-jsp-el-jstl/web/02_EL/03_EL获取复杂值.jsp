<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>获取对象、List集合、Map集合的值</title>
</head>
<body>

<%--
获取对象、List集合、Map集合的值
    1. 对象：${域名称.键名.属性名}
        * 本质上会去调用对象的getter方法，通过的是对象的属性来获取
        * 属性：setter或getter方法，去掉set或get，在将剩余部分，首字母变为小写。
          setName --> Name --> name

    2. List集合：${域名称.键名[索引]}

    3. Map集合：
        * ${域名称.键名.key名称}
        * ${域名称.键名["key名称"]}
--%>
<%

    // request.setAttribute("user", );
%>

<h3>获取对象的值</h3>


<h3>获取List集合的值</h3>


<h3>获取Map集合的值</h3>


</body>
</html>
