package com.example.util.发送电子邮件;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * 发送邮件
 *
 * @author yuwb@corp.21cn.com
 * @date 19-4-15 上午11:26
 */
public class SendMail {
    public static void main(String[] args) throws javax.mail.MessagingException {
        // 收件人电子邮箱
        String to = "*****@189.cn";

        // 发件人电子邮箱
        String from = "*****@***.com";

        // 指定发送邮件的主机为 smtp.qq.com
        String host = "smtp-ent.***.com";

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");

        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            public javax.mail.PasswordAuthentication getPasswordAuthentication() {
                //发件人邮件用户名、密码
                return new PasswordAuthentication("***@*****.com", "********d");
            }
        });

        // 创建默认的 MimeMessage 对象
        MimeMessage message = new MimeMessage(session);

        // Set From: 头部头字段
        message.setFrom(new InternetAddress(from));

        // Set To: 头部头字段
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to2));

        // Set Subject: 头部头字段
        message.setSubject("Log Notification");

        // 设置消息体
        message.setText("The program has been executed, and please check the log");

        // 创建消息部分
        BodyPart messageBodyPart = new MimeBodyPart();

        // 消息
        messageBodyPart.setText("The program has been executed, and please check the log");

        // 创建多重消息
        Multipart multipart = new MimeMultipart();

        // 设置文本消息部分
        multipart.addBodyPart(messageBodyPart);

        // 附件部分
        messageBodyPart = new MimeBodyPart();
        String fileSource = "*****.txt";
        DataSource source = new FileDataSource(fileSource);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName("log.txt");
        multipart.addBodyPart(messageBodyPart);

        // 发送完整消息
        message.setContent(multipart);

        // 发送消息
        Transport.send(message);
    }
}
