package com.netcracker.group5.medkit.service;

import java.util.List;

public interface NotificationService {

    void bulkCreateNotifications(Long userId, List<Long> prescriptionItemIdList);

    void bulkDeleteNotifications();
}
