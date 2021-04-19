package com.ashen.design.pattern.creational.prototype;

import java.text.MessageFormat;

public class MailUtil {

    // 发送邮件
    public static void sendMail(Mail mail) {
        String outputContent = "向{0}同学发送邮件,邮件地址:{1},邮件内容:{2}";
        System.out.println(MessageFormat.format(outputContent, mail.getName(), mail.getEmailAddress(), mail.getContent()));
    }

    // 存储初始化模板
    public static void saveOriginMailRecord(Mail mail) {
        System.out.println("存储Mail初始化模板,originMail:" + mail.getContent());
    }
}
