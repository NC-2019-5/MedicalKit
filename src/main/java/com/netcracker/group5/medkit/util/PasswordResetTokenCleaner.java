package com.netcracker.group5.medkit.util;

import com.netcracker.group5.medkit.service.PasswordResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PasswordResetTokenCleaner {

    @Autowired
    private PasswordResetTokenService resetTokenService;

    @Scheduled(cron = "${spring.cron.expression}")
    public void clearTokens(){
        System.out.println("Start password reset token clear...");
        resetTokenService.bulkDeleteToken();
        System.out.println("Shut down reset token clear...");
    }
}
