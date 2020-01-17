package com.netcracker.group5.medkit.repository.impl;

import com.netcracker.group5.medkit.repository.NotificationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationRepositoryImpl implements NotificationRepository {

    @Override
    public void bulkCreateNotifications(Long userId, List<Long> prescriptionItemIdList) {

    }

    @Override
    public void bulkDeleteNotifications() {

    }
}
