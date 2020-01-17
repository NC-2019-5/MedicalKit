package com.netcracker.group5.medkit.service.impl;

import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.User;
import com.netcracker.group5.medkit.repository.NotificationRepository;
import com.netcracker.group5.medkit.repository.PatientRepository;
import com.netcracker.group5.medkit.repository.PrescriptionRepository;
import com.netcracker.group5.medkit.service.NotificationAutoGeneratorService;
import com.netcracker.group5.medkit.service.NotificationService;
import com.netcracker.group5.medkit.service.PatientService;
import com.netcracker.group5.medkit.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationAutoGeneratorServiceServiceImpl implements NotificationAutoGeneratorService {

    @Autowired
    private PatientService patientService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private PrescriptionService prescriptionService;

    @Override
    public void generateNotification() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Patient patient = patientService.getPatientByUserId(currentUser.getId());

        notificationService.bulkCreateNotifications(currentUser.getId() , prescriptionService.findActivePrescriptionItems(patient.getId()));
    }
}
