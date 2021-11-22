package com.fast.email;


import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname EmailController
 * @Created by whf
 */
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;
    //发送邮件的模板引擎
    @Autowired
    private FreeMarkerConfigurer configurer;

    @PostMapping("")
    public void send(@Validated @RequestBody EmailDto emailDto) {
        emailService.sendSimpleMail(emailDto.getEmail(), emailDto.getTitle(), emailDto.getContent());
    }

    /**
     * 发送html邮件
     *
     * @param emailDto
     */
    @PostMapping("/htmlEmail")
    public void htmlEmail(@Validated @RequestBody EmailDto emailDto) {
        try {
            String email = emailDto.getEmail();
            Template template = configurer.getConfiguration().getTemplate("template.ftl");
            String emailTitle = CommonConstant.EMAIL_REGISTER_TITLE;
            String htmlTitle = CommonConstant.EMAIL_REGISTER_CONTENT_TITLE;
            String htmlSubTitle = CommonConstant.EMAIL_REGISTER_CONTENT_SUB_TITLE;
            Map<String, Object> params = new HashMap<>();
            params.put("name", email);
            params.put("code", getCode());
            params.put("title", htmlTitle);
            params.put("subTitle", htmlSubTitle);
            params.put("time", CommonConstant.REDIS_VERIFICATION_CODE_TIME_OUT / 60 + "");
            //赋值后的模板邮件内容
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
            emailService.sendHtmlMail(email,
                    emailTitle,
                    text);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 邮件带有图片logo
     *
     * @param emailDto
     */
    @PostMapping("/sendResourceMail")
    public void sendResourceMail(@Validated @RequestBody EmailDto emailDto) {
        try {
            String imagePath = "email/logo.png";
            emailService.sendResourceMail(emailDto.getEmail(), CommonConstant.EMAIL_REGISTER_CONTENT_TITLE
                    , "<div>hello,这是一封带图片资源的邮件：" +
                            "这是图片1：<div><img src='imgId:imgPath'/></div></div>", imagePath, "imgId");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 生成6位随机数验证码
     *
     * @return
     */
    private String getCode() {
        String vcode = "";
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int) (Math.random() * 9);
        }
        return vcode;
    }
}
