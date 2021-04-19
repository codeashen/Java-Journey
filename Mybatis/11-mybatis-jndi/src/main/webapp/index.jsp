<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="org.apache.ibatis.io.Resources" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ashen.jndi.dao.IUserDao" %>
<%@ page import="com.ashen.jndi.domain.User" %>
<html>
<body>
<h2>测试JNDI方式配置连接池</h2>

<%
    // 1.使用Resource读取配置文件，生成字节输入流（mybatis读取配置文件的方法）
    InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");

    // 2.创建SqlSessionFactory工厂（构建者模式）
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    SqlSessionFactory factory = builder.build(in);

    // 3.使用工厂生产SqlSession对象（工厂模式）
    SqlSession sqlSession = factory.openSession();

    // 4.使用SqlSession创建dao的代理对象（代理模式）
    IUserDao userDao = sqlSession.getMapper(IUserDao.class);

    // 5.使用代理对象执行方法
    List<User> users = userDao.findAll();
    for (User user : users) {
        System.out.println(user);
        out.write(user.toString() + "<br>");
    }

    // 6.释放资源
    sqlSession.close();
    in.close();
%>
</body>
</html>
