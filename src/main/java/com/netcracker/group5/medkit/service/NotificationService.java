package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.request.Notification;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NotificationService {

    void bulkCreateNotifications(Long userId, List<Long> prescriptionItemIdList);

    void bulkDeleteNotifications();

    void autoDecrementMI(Notification confirmedNotification);

    List<Notification> findNotifications(Pageable pageable, String searchQuery);

    void bulkCreateMNotifications(Long userId);

    void bulkDeleteMNotifications();

    List<Notification> findMNotifications(Pageable pageable, String searchQuery);

    void deleteNotification(Long id);
}
