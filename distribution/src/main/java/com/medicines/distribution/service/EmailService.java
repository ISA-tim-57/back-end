package com.medicines.distribution.service;

import com.medicines.distribution.model.BasicUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    @Value("${spring.mail.username}")
    private String emailUsername;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;


    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailUsername);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        System.out.println(emailUsername);
        javaMailSender.send(message);
    }

    @Async
    public void sendNotificaitionAsync(BasicUser basicUser) throws MailException, InterruptedException {

        Thread.sleep(10000);
        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(basicUser.getUser().getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Account activation");
        mail.setText("HI," + basicUser.getName() + ",To activate your account, please click the following link:\n\nhttp://localhost:4200/verify/" + basicUser.getUser().getId() + "\n\nGoodbye!");
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
    }










}
