package com.ashen.web.response;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 生成验证码图片，输出到页面
 * 页面在checkCode.html
 */
@WebServlet("/checkCode")
public class VerificationCode extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 100;
        int height = 50;

        // 1.创建一个对象，在内存中的图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);  //宽、高、图片类型

        // 2.获取画笔对象，构建图片   主要方法：fillXxx  drawXxx
        Graphics g = image.getGraphics();  // 画笔对象
        g.setColor(Color.PINK);  // 画笔颜色

        // 填充背景色
        g.fillRect(0, 0, width, height);
        // 画边框
        g.setColor(Color.BLUE);
        g.drawRect(0, 0, width - 1, height - 1);

        // 验证码内容
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        for (int i = 1; i <= 4; i++) {
            // 获取随机字符
            int index = random.nextInt(str.length());
            char c = str.charAt(index);
            // 图片上写验证码字符
            g.drawString(c+"", width/5*i, height/2);
        }

        // 画干扰线
        g.setColor(Color.GREEN);
        for (int i = 0; i < 10; i++) {
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);

            g.drawLine(x1, y1, x2, y2);
        }

        // 3.将图片输出到页面
        ImageIO.write(image, "jpg", response.getOutputStream()); // 图片, 图片格式, 输出流
        // 输出流选response获取的字节输出流，将图片响应给浏览器

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
