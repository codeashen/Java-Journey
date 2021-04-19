package com.ashen.web.servletcontext;

import com.ashen.web.utils.DownLoadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/*
验证码案例：
	* 文件下载需求：
		1. 页面显示超链接
		2. 点击超链接后弹出下载提示框
		3. 完成图片文件下载

	* 分析：
		1. 超链接指向的资源如果能够被浏览器解析，则在浏览器中展示，如果不能解析，则弹出下载提示框。不满足需求
		2. 任何资源都必须弹出下载提示框
		3. 使用响应头设置资源的打开方式：
			* content-disposition:attachment;filename=xxx

	* 步骤：
		1. 定义页面，编辑超链接href属性，指向Servlet，传递资源名称filename
		2. 定义Servlet
			1. 获取文件名称
			2. 使用字节输入流加载文件进内存
			3. 指定response的响应头： content-disposition:attachment;filename=xxx
			4. 将数据写出到response输出流

	* 问题：
		* 中文文件问题
			* 解决思路：
				1. 获取客户端使用的浏览器版本信息
				2. 根据不同的版本信息，设置filename的编码方式不同
 */
@WebServlet("/download")
public class Download extends HttpServlet {
    // 对应download.html
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1.获取文件名称，找到对应资源文件
        String filename = request.getParameter("filename"); //超链接过来的get请求，请求参数中文不会乱码

        ServletContext servletContext = this.getServletContext();
        String realPath = servletContext.getRealPath("/resource/" + filename);

        // 2.用字节输入流加载文件进内存
        FileInputStream fis = new FileInputStream(realPath);

        // 3.设置响应头
        // 3.1 设置响应内容格式
        response.setHeader("content-type", servletContext.getMimeType(filename));

        // 3.2 设置浏览器打开方式——附件形式
        // 解决响应中文文件名乱码问题
        filename = DownLoadUtils.getFileName(request.getHeader("user-agent"), filename);
        System.out.println("编码后filename = " + filename);
        response.setHeader("content-disposition", "attachment;filename=" + filename);

        // 4.将内存中数据写出到响应字节输出流
        ServletOutputStream sos = response.getOutputStream();

        byte[] buff = new byte[1024 * 8];
        int len = 0;
        while ((len = fis.read(buff)) != -1) {
            sos.write(buff, 0, len);
        }

        fis.close(); //响应输出流无需关闭

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
