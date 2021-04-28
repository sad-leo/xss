package com.longlysmile.service.impl;

import com.longlysmile.config.GlobalParameters;
import com.longlysmile.util.XssFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @description: 邮件服务类
 * @author: WuJie
 * @create: 2021-04-23 13:17
 **/
@Slf4j
@Service
public class MailService {

    @Resource
    private JavaMailSender mailSender;

    public static final String SUBJECT = "XSS预警消息提示";

    /**
     * 发送附件的邮件方法
     *
     * @param content 内容
     */
    public void sendMail(String content) throws MessagingException {
        log.info("邮件发送 ==> 发件人：【{}】，收件人：【{}】，主题：【{}】", GlobalParameters.SENDER, GlobalParameters.RECIPIENT, SUBJECT);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(GlobalParameters.SENDER);
        helper.setTo(GlobalParameters.RECIPIENT);
        helper.setSubject(SUBJECT);
        helper.setText(msgTemplate(content), true);
        mailSender.send(message);
    }

    /**
     * 消息模板
     *
     * @param content 内容
     * @return 模板数据
     */
    public String msgTemplate(String content) {
        return "<div style=\"width: 50%;border: 1px solid #9e9e9e4d;padding: 20px;margin: auto;\">" +
                "<p>尊敬的用户您好：</p>" +
                "<p style=\"text-indent: 30px;\">您的WEB应用遭到XSS攻击，可疑内容为：</p>" +
                "<p style=\"text-indent: 30px;\">" + XssFilter.transform(content) +"</p>" +
                "<div style=\"border-top: 1px solid #9e9e9e4d; width: 100%\"></div>" +
                "<p style=\"text-align: center\">XSS预警平台</p>" +
                "</div>";
    }


}
