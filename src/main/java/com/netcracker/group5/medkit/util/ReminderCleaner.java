package com.netcracker.group5.medkit.util;

import com.netcracker.group5.medkit.repository.impl.UserRepositoryImpl;
import com.netcracker.group5.medkit.service.NotificationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty("spring.cron.expression")
public class ReminderCleaner {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private NotificationService notificationService;

    @Scheduled(cron = "${spring.cron.expression}")
    public void clearReminders() {
        logger.info("Start reminder cleaner...");
        System.out.println("Start reminder cleaner...");
        notificationService.bulkDeleteNotifications();
        notificationService.bulkDeleteMNotifications();
        logger.info("Shut down reminder cleaner...");
        System.out.println("Shut down reminder cleaner");
    }
}
