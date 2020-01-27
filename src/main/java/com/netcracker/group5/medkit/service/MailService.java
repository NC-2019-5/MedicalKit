package com.netcracker.group5.medkit.service;

public interface MailService {

    void sendForgotPasswordMail(String userEmail, String linkWithToken);

}
