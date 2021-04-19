<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" language="java" %>
<html>
<head>
    <title>错误提示页面</title>
</head>
<body>

<%--
page指令中设置属性 isErrorPage="true"
才可以使用JSP内置对象 exception
--%>
<h3>出错啦，错误信息：</h3>
<%= exception.getMessage()%>

</body>
</html>
