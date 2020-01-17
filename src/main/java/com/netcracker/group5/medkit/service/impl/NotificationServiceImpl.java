package com.netcracker.group5.medkit.service.impl;

import com.netcracker.group5.medkit.repository.NotificationRepository;
import com.netcracker.group5.medkit.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void bulkCreateNotifications(Long userId, List<Long> prescriptionItemIdList) {
        notificationRepository.bulkCreateNotifications(userId, prescriptionItemIdList);
    }

    @Override
    public void bulkDeleteNotifications() {
        notificationRepository.bulkDeleteNotifications();
    }
}
