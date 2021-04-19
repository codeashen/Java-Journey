<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>SpringMVC常用注解</title>
</head>
<body>
<h1>SpringMVC常用注解</h1>

<h3>注解一：RequestParam</h3>
<a href="anno/testRequestParam?name=小明">anno/testRequestParam?name=小明</a>

<h3>注解二：RequestBody</h3>
<form action="anno/testRequestBody" method="post">
    姓名：<input type="text" name="name"><br>
    年龄：<input type="text" name="age"><br>
    <input type="submit" value="提交">
</form>

<h3>注解三：PathVariable</h3>
<a href="anno/testPathVariable/5">anno/testPathVariable/5</a>

<h3>注解四：RequestHeader</h3>
<a href="anno/testRequestHeader">anno/testRequestHeader</a>


<h3>注解五：CookieValue</h3>
<a href="anno/testCookieValue">anno/testCookieValue</a>

<h3>其他注解</h3>
<a href="ModelAttribute.jsp">ModelAttribute</a><br>
<a href="SessionAttributes.jsp">SessionAttributes</a>

</body>
</html>
