package com.netcracker.group5.medkit.repository;

import java.util.List;

public interface NotificationRepository {
    void bulkCreateNotifications(Long userId, List<Long> prescriptionItemIdList);

    void bulkDeleteNotifications();
}
