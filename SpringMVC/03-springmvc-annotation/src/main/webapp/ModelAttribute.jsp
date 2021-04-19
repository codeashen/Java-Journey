<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ModelAttribute注解</title>
</head>
<body>
<h1>ModelAttribute注解</h1>

<h3>方式一：ModelAttribute注解方法有返回值</h3>
<form action="testModelAttribute/test1" method="post">
    用户名：<input type="text" name="username"><br>
    密码：<input type="text" name="password"><br>
    <input type="submit" value="提交">
</form>

<h3>方式二：ModelAttribute注解方法无返回值</h3>
<form action="testModelAttribute/test2" method="post">
    用户名：<input type="text" name="username"><br>
    密码：<input type="text" name="password"><br>
    <input type="submit" value="提交">
</form>

</body>
</html>
