package com.netcracker.group5.medkit.util;

import com.netcracker.group5.medkit.repository.impl.UserRepositoryImpl;
import com.netcracker.group5.medkit.service.NotificationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "spring.cron", name = "expression")
public class ReminderCleaner {
    private static final Logger log = Logger.getLogger(ReminderCleaner.class);

    @Autowired
    private NotificationService notificationService;

    @Scheduled(cron = "${spring.cron.expression}")
    public void clearReminders() {
        log.info("Start reminder cleaner...");
        System.out.println("Start reminder cleaner...");
        notificationService.bulkDeleteNotifications();
        notificationService.bulkDeleteMNotifications();
        log.info("Shut down reminder cleaner...");
        System.out.println("Shut down reminder cleaner");
    }
}
