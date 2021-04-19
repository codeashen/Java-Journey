<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>改变请求方式</title>
</head>
<body>
<h1>HiddenHttpMethodFilter拦截器改变请求方式</h1>


<h3>post请求：保存</h3>
<form action="method/testRest" method="post">
    用户名：<input type="text" name="username"><br/>
    <!-- <input type="hidden" name="_method" value="POST"> -->
    <input type="submit" value="保存">
</form>


<h3>后台改为put请求：更新</h3>
<form action="method/testRest/1" method="post">
    用户名：<input type="text" name="username"><br/>
    <input type="hidden" name="_method" value="PUT">
    <input type="submit" value="更新">
</form>


<h3>后台改为delete请求：删除</h3>
<form action="method/testRest/1" method="post">
    <input type="hidden" name="_method" value="DELETE">
    <input type="submit" value="删除">
</form>


<h3>后台改为get请求：查询</h3>
<form action="method/testRest/1" method="post">
    <input type="hidden" name="_method" value="GET">
    <input type="submit" value="查询">
</form>

</body>
</html>
