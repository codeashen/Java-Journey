package com.ashen.web.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
ServletContext功能：
    1.获取MIME类型：
    2.域对象：共享数据

    3.获取文件的真实(服务器)路径
        资源都是在服务器的对应web项目的路径下，本项目采用配置文件部署，项目不在Tomcat的webapps下
        F:\IdeaProjects\itcastNo57\out\artifacts\01_Servlet_HTTP_war_exploded\...

        方法：String getRealPath(String path)
            1) 获取web目录下文件的服务器路径
               开发 web -------->  服务器 web
                 String b = context.getRealPath("/b.txt");//web目录下资源访问

            2) 获取web/WEB-INF目录下文件的服务器路径
               开发 web/WEB-INF -------->  服务器 web/WEB-INF
                String c = context.getRealPath("/WEB-INF/c.txt");//WEB-INF目录下的资源访问

            3) 获取src目录下文件的服务器路径
               开发 src -------->  服务器 web/WEB-INF/classes
                String a = context.getRealPath("/WEB-INF/classes/a.txt");//src目录下的资源访问

 */
/*
复习：
    类加载器 ClassLoader.getResource 获取文件路径，只能获取src目录下的资源
    web项目的 Context.getRealPath 可以获取web项目任意目录下的资源
 */
@WebServlet("/realPath")
public class RealPath extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 获取ServletContext对象
        ServletContext servletContext = this.getServletContext();


        // 获取web目录下文件的服务器路径
        // F:\IdeaProjects\itcastNo57\out\artifacts\01_Servlet_HTTP_war_exploded\b.txt
        String b = servletContext.getRealPath("/b.txt");

        // 获取web/WEB-INF目录下文件的服务器路径
        // F:\IdeaProjects\itcastNo57\out\artifacts\01_Servlet_HTTP_war_exploded\WEB-INF\c.txt
        String c = servletContext.getRealPath("/WEB-INF/c.txt");

        // 获取src目录下文件的服务器路径
        // F:\IdeaProjects\itcastNo57\out\artifacts\01_Servlet_HTTP_war_exploded\WEB-INF\classes\a.txt
        String a = servletContext.getRealPath("/WEB-INF/classes/a.txt");

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
