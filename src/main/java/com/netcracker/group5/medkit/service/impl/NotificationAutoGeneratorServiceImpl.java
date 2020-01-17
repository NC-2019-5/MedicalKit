package com.netcracker.group5.medkit.service.impl;

import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.service.NotificationAutoGeneratorService;
import com.netcracker.group5.medkit.service.NotificationService;
import com.netcracker.group5.medkit.service.PatientService;
import com.netcracker.group5.medkit.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationAutoGeneratorServiceImpl implements NotificationAutoGeneratorService {

    @Autowired
    private PatientService patientService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private PrescriptionService prescriptionService;

    @Override
    public void generateNotification(Long userId) {
        Patient patient = patientService.getPatientByUserId(userId);

        notificationService.bulkCreateNotifications(userId, prescriptionService.findActivePrescriptionItems(patient.getId()));
        notificationService.bulkDeleteNotifications();
    }
}
