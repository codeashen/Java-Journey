<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>EL运算符</title>
</head>
<body>

<%--
EL使用
2.获取值
    1. el表达式只能从域对象中获取值
    2. 语法：
        1. ${域名称.键名}：从指定域中获取指定键的值
            * 域名称：
                1. pageScope		--> pageContext
                2. requestScope 	--> request
                3. sessionScope 	--> session
                4. applicationScope --> application（ServletContext）
            * 举例：在request域中存储了name=张三
            * 获取：${requestScope.name}

        2. ${键名}：表示依次从最小的域中查找是否有该键对应的值，直到找到为止。

        3. 获取对象、List集合、Map集合的值
            1. 对象：${域名称.键名.属性名}
                * 本质上会去调用对象的getter方法

            2. List集合：${域名称.键名[索引]}

            3. Map集合：
                * ${域名称.键名.key名称}
                * ${域名称.键名["key名称"]}
--%>
<%
    request.setAttribute("name", "zhangsan");
    session.setAttribute("code", "abcd");
%>

<h3>指定范围</h3>
requestScope.name = ${requestScope.name} <br>
sessionScope.code = ${sessionScope.code} <br>
sessionScope.haha = ${sessionScope.haha}

<h3>不指定范围</h3>
name = ${name} <br>
code = ${code}

</body>
</html>
