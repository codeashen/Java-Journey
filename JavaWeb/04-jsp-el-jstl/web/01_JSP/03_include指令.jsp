<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>include指令</title>
</head>
<body>

<%--个人觉得：页面包含的时候，将jsp多余部分去掉最好--%>
<%@ include file="../header.jsp"%>

<h1>页面主体</h1>

<%@ include file="../footer.jsp"%>

</body>
</html>

<%--本页在浏览器的网页源代码：

<html>
<head>
    <title>include指令</title>
</head>
<body>


<h2>页面头部</h2>

<h1>页面主体</h1>

<html>
<head>
    <title>尾部</title>
</head>
<body>
<h2>页面尾部</h2>
</body>
</html>

</body>
</html>


--%>

