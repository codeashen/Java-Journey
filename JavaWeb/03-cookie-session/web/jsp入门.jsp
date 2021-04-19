<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp入门</title>
</head>
<body>

<%--
JSP的脚本：JSP定义Java代码的方式
    1. <%  代码 %>：定义的java代码，在service方法中。service方法中可以定义什么，该脚本中就可以定义什么。
    2. <%! 代码 %>：定义的java代码，在jsp转换后的java类的成员位置。类的成员位置能写啥，该脚本就能写啥。
    3. <%= 代码 %>：定义的java代码，会输出到页面上，位置再service方法里。输出语句中可以定义什么，该脚本中就可以定义什么。
--%>

<%
    int a = 2; //service方法的局部变量
    System.out.println("hello jsp"); //控制台输出
%>

<%! int a = 1; //成员变量  %>

<%--输出 2，因为转化的servlet，输出语句在service方法里，优先访问局部变量--%>
<%= a %>

</body>
</html>
