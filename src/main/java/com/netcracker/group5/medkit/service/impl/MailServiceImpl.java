package com.netcracker.group5.medkit.service.impl;

import com.netcracker.group5.medkit.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendForgotPasswordMail(String userEmail, String linkWithToken) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(userEmail);
        mailMessage.setSubject("Reset password request");
        mailMessage.setText("To reset your password, click the link: " + linkWithToken);

        javaMailSender.send(mailMessage);
    }
}
