package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.service.EmailService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
@Setter
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String emailAddress;
    @Value("${bookstore.mail.enable}")
    private Boolean isEnableEmailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendEmail(String to, String subject, String templateName, Context context) {
        if (!isEnableEmailSender) return;

        var content = generateContentForEmail(templateName, context);

        MimeMessage message = emailSender.createMimeMessage();
        sendMessage(message, content, to, subject);
    }

    private void sendMessage(MimeMessage message, String content, String to, String subject) {
        try {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(() -> {
                try {
                    MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                    helper.setFrom(emailAddress);
                    helper.setTo(to);
                    helper.setSubject(subject);
                    helper.setText(content, true);
                    emailSender.send(message);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });
            executorService.shutdown();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    private String generateContentForEmail(String templateName, Context context) {
        return templateEngine.process(templateName, context);
    }
}
