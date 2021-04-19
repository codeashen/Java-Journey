<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>

    <script>
        window.onload = function () {
            var img_code = document.getElementById("img_code");
            img_code.onclick = function () {
                img_code.src = "/session/checkCode?time=" + new Date().getTime();
            }
        }
    </script>

    <style>
        #error {
            color: red;
        }
    </style>
</head>
<body>

<form action="/session/login" method="post">
    <table>
        <tr>
            <td>用户名</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>验证码</td>
            <td><input type="text" name="checkCode"></td>
        </tr>
        <tr>
            <td></td>
            <td><img id="img_code" src="/session/checkCode"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="登录"></td>
        </tr>
    </table>
</form>

<div id="error">
    <%--
    EL表达式之前写法
    <%= request.getAttribute("msg") == null ? "" : request.getAttribute("msg")%>
    --%>
    <%--EL表达式写法--%>
    ${requestScope.msg}
</div>

</body>
</html>
