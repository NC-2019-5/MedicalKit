package com.netcracker.group5.medkit.model.dto.notification;

import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.request.Notification;

import java.util.List;

public class NotificationResponseItem implements Requestable {

    private List<Notification> notifications;

    public NotificationResponseItem(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
