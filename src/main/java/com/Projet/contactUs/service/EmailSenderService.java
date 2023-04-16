package com.Projet.contactUs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.Projet.contactUs.emailRequestClass.EmailRequest;

@Service
public class EmailSenderService {
	
	@Autowired
    private JavaMailSender mailSender;

    public void sendEmail(EmailRequest emailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("exemple email@gmail.com");
        message.setTo(emailRequest.getToEmail());
        message.setText(emailRequest.getBody());
        message.setSubject(emailRequest.getSubject());

        mailSender.send(message);
    }


}