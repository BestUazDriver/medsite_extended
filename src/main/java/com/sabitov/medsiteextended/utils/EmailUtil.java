package com.sabitov.medsiteextended.utils;

import freemarker.template.Configuration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class EmailUtil {

    private final JavaMailSender mailSender;
    private final Configuration configuration;

    @Value("${spring.mail.username}")
    private String from;

    public void sendMail(String to, String subject, String templateName, Map<String, String> data) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            String mailText = FreeMarkerTemplateUtils
                    .processTemplateIntoString(configuration
                            .getTemplate(templateName, "UTF-8"), data);
            messageHelper.setSubject(subject);
            messageHelper.setText(mailText, true);
            messageHelper.setTo(to);
            messageHelper.setFrom(from);
        };
        mailSender.send(preparator);
    }
}

