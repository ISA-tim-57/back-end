package com.medicines.distribution.service;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    @Value("${spring.mail.username}")
    private String emailUsername;

    @Autowired
    private JavaMailSender javaMailSender;


    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailUsername);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        System.out.println(emailUsername);
        javaMailSender.send(message);
    }
}
