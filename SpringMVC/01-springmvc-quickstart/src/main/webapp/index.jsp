<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>SpringMVC快速入门</title>
</head>
<body>
<h2>SpringMVC快速入门</h2>

<%-- 方式一 --%>
<a href="${pageContext.request.contextPath}/quick/hello">开始入门</a>
<%-- 方式二 --%>
<a href="quick/hello">开始入门</a>

<%-- 当我们使用此种方式配置时，在jsp 中第二种写法时，不要在访问URL 前面加/，否则无法找到资源 --%>

</body>
</html>
