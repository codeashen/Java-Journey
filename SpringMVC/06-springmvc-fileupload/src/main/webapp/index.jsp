<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<h1>文件上传</h1>

<pre>
文件上传的必要前提
    1. form表单的enctype取值必须是：multipart/form-data
        (默认值是:application/x-www-form-urlencoded)
        enctype:是表单请求正文的类型
    2. method属性取值必须是Post
    3. 提供一个文件选择域&lt;input type=”file” /&gt;
</pre>

<h3>传统JavaWeb文件上传</h3>
<form action="file/fileupload1" method="post" enctype="multipart/form-data">
    选择文件<input type="file" name="upload"><br>
    <input type="submit" value="上传">
</form>

<h3>SpringMVC文件上传</h3>
<form action="file/fileupload2" method="post" enctype="multipart/form-data">
    选择文件<input type="file" name="upload"><br>
    <input type="submit" value="上传">
</form>

<h3>跨服务器文件上传</h3>
<form action="file/fileupload3" method="post" enctype="multipart/form-data">
    选择文件<input type="file" name="upload"><br>
    <input type="submit" value="上传">
</form>
<pre>
出错看情况
    409：看文件服务器target项目目录下是否有uploads目录，没有新建
    405：修改Tomcat配置，参考<a href="https://www.jianshu.com/p/484e68a0c4c0">这里</a>
</pre>

</body>
</html>
