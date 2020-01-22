package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.request.Notification;

import java.util.List;

public interface NotificationService {

    void bulkCreateNotifications(Long userId, List<Long> prescriptionItemIdList);

    void bulkDeleteNotifications();

    void autoDecrementMI(Notification confirmedNotification);
}
