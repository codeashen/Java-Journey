<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SpringMVC响应和结果视图</title>
    <%-- 引入JQuery文件 --%>
    <script src="js/jquery.min.js"></script>
    <script>
        // 页面加载，绑定单击事件
        $(function () {
            $("#btn").click(function () {
                // 发送ajax请求
                $.ajax({
                    url : "response/testAjax",
                    contentType : "application/json;charset=utf-8",
                    type : "post",
                    data : '{"username":"张三","password":"123456","age":23}',
                    dataType : "json",
                    success : function (data) {
                        // data为服务器端响应的json的数据，进行解析
                        alert(data);
                        alert(data.username);
                        alert(data.password);
                        alert(data.age);
                    },
                    error : function () {
                        alert("出错了！");
                    }
                });
            });
        });
    </script>
</head>
<body>
<h1>SpringMVC响应和结果视图</h1>

<h2>返回值类型一：String</h2>
<a href="response/testString">返回String</a>

<h2>返回值类型二：void</h2>
<h3>用法一：请求转发</h3>
<a href="response/testVoid1">请求转发</a>
<h3>用法二：重定向</h3>
<a href="response/testVoid2">重定向</a>
<h3>用法三：通过response指定响应结果（写出json）</h3>
<a href="response/testVoid3">写出json</a>

<h2>返回值类型三：ModelAndView</h2>
<a href="response/testModelAndView">返回ModelAndView</a>
<hr>

<h2>关键字请求转发和重定向</h2>
<a href="response/testForward">请求转发</a>
<a href="response/testRedirect">重定向</a>
<hr>

<h2>ajax请求发送、接收json</h2>
<button id="btn">发送ajax请求</button>

</body>
</html>
