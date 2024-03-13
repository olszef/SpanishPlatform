package com.home.spanishplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String toEmail, String subject, String text) throws MailException {

        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(toEmail); 
        message.setSubject(subject); 
        message.setText(text);
                
        try {
        	emailSender.send(message);
        } catch (MailException mailException) {
            throw mailException;
        }
    }
}
