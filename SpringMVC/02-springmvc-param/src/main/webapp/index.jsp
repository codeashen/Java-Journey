<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>请求参数绑定</title>
</head>
<body>
<h1>请求参数绑定</h1>

<h2>一、基本数据类型的绑定</h2>
<form action="login">
    用户名：<input type="text" name="username"><br>
    密码：<input type="password" name="password"><br>
    <input type="submit" value="提交">
</form>

<h2>二、参数绑定到对象（对象中含对象）</h2>
<form action="info">
    姓名：<input type="text" name="name"><br>
    年龄：<input type="text" name="age"><br>
    身份证号：<input type="text" name="idCard.idNumber"><br>
    <input type="submit" value="提交">
</form>

<h2>三、参数绑定到对象（对象中有集合）</h2>
<form action="bank">
    银行名称：<input type="text" name="bankName"><br>
    <b>账户List：</b><br>
        账户一：<br>
        用户名：<input type="text" name="accounts[0].username" value="用户名"><br>
        密码：<input type="text" name="accounts[0].password" value="密码"><br>
    <b>用户Map：</b><br>
        VIP：<br>
        姓名：<input type="text" name="members['vip'].name"><br>
        年龄：<input type="text" name="members['vip'].age"><br>
    <input type="submit" value="提交">
</form>

<h2>四、自定义类型转换器（转换yyyy-MM-dd格式日期）</h2>
<form action="info">
    姓名：<input type="text" name="name"><br>
    年龄：<input type="text" name="age"><br>
    生日：<input type="text" name="birthday"><br>
    <input type="submit" value="提交">
</form>

<h2>五、绑定原生Servlet的API</h2>
<a href="servlet">获取Servlet原生API</a>

</body>
</html>
