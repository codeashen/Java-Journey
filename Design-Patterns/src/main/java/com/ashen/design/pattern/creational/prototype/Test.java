package com.ashen.design.pattern.creational.prototype;

public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        Mail mail = new Mail();
        mail.setContent("初始化模板");

        for (int i = 0; i < 10; i++) {
            // 克隆对象，不会调用构造器，和原对象是不同的对象
            Mail tempMail = (Mail) mail.clone();

            tempMail.setName("姓名" + i);
            tempMail.setEmailAddress("姓名" + i + "@gmail.com");
            tempMail.setContent("供你您，中奖啦");
            MailUtil.sendMail(tempMail);
        }

        MailUtil.saveOriginMailRecord(mail);
    }
}
