package com.netcracker.group5.medkit.util;

import com.netcracker.group5.medkit.model.domain.request.Notification;
import com.netcracker.group5.medkit.model.domain.request.NotificationType;
import com.netcracker.group5.medkit.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationMessageBuilder {

    public static final String USER_NAME_ALIAS = "<name>";
    public static final String MEDICINE_NAME_ALIAS = "<name>";

    private String templateReminder = "Dear " + USER_NAME_ALIAS + ", it's time to take " + MEDICINE_NAME_ALIAS;

    @Autowired
    private PatientService patientService;

    public String buildMessage(Notification notification) {
        if (notification.getType().equals(NotificationType.REMINDER)) {
            String userName = patientService.getPatient(notification.getUserId()).getName();
            String medicineName = notification.getPrescriptionItem().getMedicine().getName();

            return templateReminder
                    .replace(USER_NAME_ALIAS, userName)
                    .replace(MEDICINE_NAME_ALIAS, medicineName);
        }

        throw new IllegalArgumentException("Unsupported notification type " + notification.getType());
    }
}
