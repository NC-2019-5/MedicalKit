package com.netcracker.group5.medkit.service.impl;

import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;
import com.netcracker.group5.medkit.model.domain.request.Notification;
import com.netcracker.group5.medkit.repository.NotificationRepository;
import com.netcracker.group5.medkit.service.MedicineInstanceService;
import com.netcracker.group5.medkit.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private MedicineInstanceService medicineInstanceService;

    @Override
    public void bulkCreateNotifications(Long userId, List<Long> prescriptionItemIdList) {
        notificationRepository.bulkCreateNotifications(userId, prescriptionItemIdList);
    }

    @Override
    public void bulkDeleteNotifications() {
        notificationRepository.bulkDeleteNotifications();
    }

    @Override
    public void autoDecrementMI(Notification confirmedNotification) {
        MedicineInstance medicineInstance = confirmedNotification.getMedicineInstance();
        if (medicineInstance.getAmount() >= confirmedNotification.getPrescriptionItem().getDosage()) {
            medicineInstance.setAmount(medicineInstance.getAmount() - confirmedNotification.getPrescriptionItem().getDosage());
            medicineInstanceService.saveMedicineInstance(medicineInstance);
            notificationRepository.deleteNotification(confirmedNotification.getId());
        }
    }
}
