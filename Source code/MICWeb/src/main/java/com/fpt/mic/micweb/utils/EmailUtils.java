package com.fpt.mic.micweb.utils;

import com.sun.mail.smtp.SMTPTransport;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/25/15.
 */
public class EmailUtils {
    public static final String SUBJECT_NEW_CONTRACT = "Mật khẩu hợp đồng mới với công ty bảo hiểm MIC";


    private static final String SMTP_HOST = "smtp.mailgun.org";
    // TODO: this is sensitive information and should not be here in the source code
    private static final String SMTP_USERNAME = "mic@sandbox48f01f256473463db6872804212b4bc9.mailgun.org";
    private static final String SMTP_PASSWORD = "micweb";
    private static final String EMAIL_FROM = "no-reply@mic.vn";
    private static final String EMAIL_MAILER = "Công ty bảo hiểm MIC";
    public static boolean sendMail(String to, String subject, String content) {
        boolean response = false;
        try {
            Properties props = System.getProperties();
            props.put("mail.smtps.host", SMTP_HOST);
            props.put("mail.smtps.auth", "true");
            Session session = Session.getInstance(props, null);
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(EMAIL_FROM));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to, false));
            msg.setSubject(subject);
            msg.setContent(content, "text/html");
            msg.setHeader("X-Mailer", EMAIL_MAILER);
            msg.setSentDate(new Date());
            SMTPTransport t =
                    (SMTPTransport) session.getTransport("smtps");
            t.connect(SMTP_HOST, SMTP_USERNAME, SMTP_PASSWORD);
            t.sendMessage(msg, msg.getAllRecipients());
            response = t.getLastReturnCode() == 250;
            System.out.println("Response: " + t.getLastServerResponse() + " (" + t.getLastReturnCode() + ")");
            t.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return response;
    }
}
