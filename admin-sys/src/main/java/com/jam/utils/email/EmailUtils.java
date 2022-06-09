package com.jam.utils.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Slf4j
@Component
public class EmailUtils {

    @Autowired
    private final JavaMailSender javaMailSender;

    @Bean
    public JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }

    @Autowired
    public EmailUtils(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    //注入配置文件中配置的信息——>from
    @Value("${spring.mail.from}")
    private static String from;

    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        //发件人
        message.setFrom(from);
        //收件人
        message.setTo(to);
        //邮件主题
        message.setSubject(subject);
        //邮件内容
        message.setText(content);
        //发送邮件
        javaMailSender.send(message);
    }

    public void sendSimpleMail(MailSender mailSender) {
        SimpleMailMessage message = new SimpleMailMessage();
        //发件人
        message.setFrom(from);
        //收件人
        message.setTo(mailSender.getTo());
        //邮件主题
        message.setSubject(mailSender.getSubject());
        //邮件内容
        message.setText(mailSender.getContent());
        //发送邮件
        javaMailSender.send(message);
    }

    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            message.setSubject(subject);
            messageHelper.setText(content, true);
            javaMailSender.send(message);
            log.info("邮件已经发送！");
        } catch (MessagingException e) {
            log.error("发送邮件时发生异常：" + e);
        }
    }

    public void sendHtmlMail(MailSender mailSender) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(mailSender.getTo());
            message.setSubject(mailSender.getSubject());
            messageHelper.setText(mailSender.getContent(), true);
            javaMailSender.send(message);
            log.info("邮件已经发送！");
        } catch (MessagingException e) {
            log.error("发送邮件时发生异常：" + e);
        }
    }

    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);
            //携带附件
            FileSystemResource file = new FileSystemResource(filePath);
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            messageHelper.addAttachment(fileName, file);

            javaMailSender.send(message);
            log.info("邮件加附件发送成功！");
        } catch (MessagingException e) {
            log.error("发送失败：" + e);
        }
    }

    public void sendAttachmentsMail(MailSender mailSender, String filePath) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(mailSender.getTo());
            messageHelper.setSubject(mailSender.getSubject());
            messageHelper.setText(mailSender.getContent(), true);
            //携带附件
            FileSystemResource file = new FileSystemResource(filePath);
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            messageHelper.addAttachment(fileName, file);

            javaMailSender.send(message);
            log.info("邮件加附件发送成功！");
        } catch (MessagingException e) {
            log.error("发送失败：" + e);
        }
    }

}