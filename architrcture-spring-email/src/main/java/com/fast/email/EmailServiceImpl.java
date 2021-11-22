package com.fast.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Classname EmailServiceImpl
 * @Created by whf
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送文本邮件
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        sendSimpleMail(to, subject, content, null);
    }

    @Override
    public void sendSimpleMail(String to, String subject, String content, String[] ccs) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        if (ccs != null && ccs.length > 0) {
            message.setCc(ccs);
        }
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    /**
     * 发送HTML邮件
     */
    @Override
    public void sendHtmlMail(String to, String subject, String content) throws MessagingException {
        sendHtmlMail(to, subject, content, null);
    }

    @Override
    public void sendHtmlMail(String to, String subject, String content, String[] ccs) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        //true表示需要创建一个multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        if (ccs != null && ccs.length > 0) {
            helper.setCc(ccs);
        }
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }

    /**
     * 发送带附件的邮件
     */
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) throws MessagingException {
        sendAttachmentsMail(to, subject, content, filePath, null);
    }

    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath, String[] ccs) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        if (ccs != null && ccs.length > 0) {
            helper.setCc(ccs);
        }
        helper.setSubject(subject);
        helper.setText(content, true);

        FileSystemResource file = new FileSystemResource(new File(filePath));
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
        helper.addAttachment(fileName, file);

        mailSender.send(message);
    }

    /**
     * 发送正文中有静态资源（图片）的邮件
     */
    public void sendResourceMail(String to, String subject, String content, String rscPath, String rscId) throws MessagingException {
        sendResourceMail(to, subject, content, rscPath, rscId, null);
    }

    @Override
    public void sendResourceMail(String to, String subject, String content, String rscPath, String rscId, String[] ccs) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        if (ccs != null && ccs.length > 0) {
            helper.setCc(ccs);
        }
        helper.setSubject(subject);
        helper.setText(content, true);

        ClassPathResource classPathResource = new ClassPathResource(rscPath);
        helper.addInline(rscId, classPathResource);

        mailSender.send(message);
    }
}
