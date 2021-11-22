package com.fast.email;

import javax.mail.MessagingException;

/**
 * @Classname EmailService
 * @Created by whf
 */
public interface EmailService {
    /**
     * 发送文本邮件
     */
    void sendSimpleMail(String to, String subject, String content);

    void sendSimpleMail(String to, String subject, String content, String[] ccs);

    /**
     * 发送HTML邮件
     */
    void sendHtmlMail(String to, String subject, String content) throws MessagingException;

    void sendHtmlMail(String to, String subject, String content, String[] ccs) throws MessagingException;

    /**
     * 发送带附件的邮件
     */
    void sendAttachmentsMail(String to, String subject, String content, String filePath) throws MessagingException;

    void sendAttachmentsMail(String to, String subject, String content, String filePath, String[] ccs) throws MessagingException;

    /**
     * 发送正文中有静态资源的邮件
     */
    void sendResourceMail(String to, String subject, String content, String rscPath, String rscId) throws MessagingException;

    void sendResourceMail(String to, String subject, String content, String rscPath, String rscId, String[] ccs) throws MessagingException;

}
