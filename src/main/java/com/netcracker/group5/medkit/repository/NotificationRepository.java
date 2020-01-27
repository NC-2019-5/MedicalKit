package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.request.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository {
    void bulkCreateNotifications(Long userId, List<Long> prescriptionItemIdList);

    void bulkDeleteNotifications();

    void deleteNotification(Long id);

    Optional<List<Notification>> findNotifications(Long id, long limit, long offset, String searchQuery);

    void bulkCreateMNotifications(Long userId);

    void bulkDeleteMNotifications();

    Optional<List<Notification>> findMNotifications(Long id, long limit, long offset, String searchQuery);
}
