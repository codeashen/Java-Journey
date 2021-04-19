<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>账户列表</title>
</head>
<body>

<h3>查询所有的帐户结果</h3>

<table border="1px">
    <tr>
        <th>ID</th>
        <th>姓名</th>
        <th>余额</th>
    </tr>
    <c:forEach items="${list}" var="account">
        <tr>
            <td>${account.id}</td>
            <td>${account.name}</td>
            <td>${account.money}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>