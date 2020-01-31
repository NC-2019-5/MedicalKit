package com.netcracker.group5.medkit.service.impl;

import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;
import com.netcracker.group5.medkit.model.domain.prescription.PrescriptionItem;
import com.netcracker.group5.medkit.model.domain.request.Notification;
import com.netcracker.group5.medkit.model.domain.user.User;
import com.netcracker.group5.medkit.repository.NotificationRepository;
import com.netcracker.group5.medkit.service.MedicineInstanceService;
import com.netcracker.group5.medkit.service.NotificationService;
import com.netcracker.group5.medkit.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private MedicineInstanceService medicineInstanceService;

    @Autowired
    private PrescriptionService prescriptionService ;

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
        if(confirmedNotification.getMedicineInstanceId() != null) {
            MedicineInstance medicineInstance = medicineInstanceService.findMedicineInstance(confirmedNotification.getMedicineInstanceId());
            PrescriptionItem prescriptionItem = prescriptionService.findPrescriptionItem(confirmedNotification.getPrescriptionItemId());
            if(medicineInstance.getAmount() > prescriptionItem.getDosage()){
                medicineInstance.setAmount(medicineInstance.getAmount() - prescriptionItem.getDosage());
                medicineInstanceService.saveMedicineInstance(medicineInstance);
                notificationRepository.deleteNotification(confirmedNotification.getId());
            }else{
                throw new IllegalArgumentException();
            }

        }else{
            notificationRepository.deleteNotification(confirmedNotification.getId());
        }
    }

    @Override
    public List<Notification> findNotifications(Pageable pageable, String searchQuery) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long limit = pageable.getOffset() + pageable.getPageSize();

        return notificationRepository.findNotifications(currentUser.getId(), limit, pageable.getOffset(), searchQuery).orElse(null);
    }
    public void bulkCreateMNotifications(Long userId) {
        notificationRepository.bulkCreateMNotifications(userId);
    }

    @Override
    public void bulkDeleteMNotifications() {
        notificationRepository.bulkDeleteMNotifications();
    }
    @Override
    public List<Notification> findMNotifications(Pageable pageable, String searchQuery) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long limit = pageable.getOffset() + pageable.getPageSize();

        return notificationRepository.findMNotifications(currentUser.getId(), limit, pageable.getOffset(), searchQuery).orElse(null);
    }
}
