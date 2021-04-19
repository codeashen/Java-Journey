<%@ page import="com.ashen.code.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录成功</title>
</head>
<body>

<%= ((User) session.getAttribute("user")).getUsername()%>，欢迎您

</body>
</html>
