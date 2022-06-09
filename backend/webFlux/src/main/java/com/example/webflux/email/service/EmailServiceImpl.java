package com.example.webflux.email.service;


import com.example.webflux.email.context.AbstractEmailContext;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    private final SpringTemplateEngine templateEngine;


    @Override
    public void sendMail(AbstractEmailContext email) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariables(email.getContext());
        String emailContent = templateEngine.process(email.getTemplateLocation(),context);

        mimeMessageHelper.setTo(email.getTo());
        mimeMessageHelper.setSubject(email.getSubject());
        mimeMessageHelper.setFrom(email.getFrom());
        mimeMessageHelper.setText(emailContent,true);
        //mimeMessageHelper.setText("Vui lòng nhấn vào: " +url+" để kích hoạt tài khoản!" );
        mailSender.send(message);
    }
}
