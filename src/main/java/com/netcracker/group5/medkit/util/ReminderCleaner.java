package com.netcracker.group5.medkit.util;

import com.netcracker.group5.medkit.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "spring.cron", name = "expression")
public class ReminderCleaner {

    @Autowired
    private NotificationService notificationService;

    @Scheduled(cron = "${spring.cron.expression}")
    public void clearReminders(){
        notificationService.bulkDeleteNotifications();
    }

}
