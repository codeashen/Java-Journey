<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SSM整合</title>
</head>
<body>
<h1>SSM整合</h1>

<a href="account/findAll">查询所有账户</a>
<hr>

<form action="account/save" method="post">
    姓名：<input type="text" name="name" /><br/>
    金额：<input type="text" name="money" /><br/>
    <input type="submit" value="保存"/><br/>
</form>

</body>
</html>
