package com.example.webflux.email.service;



import com.example.webflux.email.context.AbstractEmailContext;

import javax.mail.MessagingException;

public interface EmailService {
    void sendMail(final AbstractEmailContext email) throws MessagingException;
}
