package com.netcracker.group5.medkit.model.domain.request;

import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.user.User;

import java.time.LocalDateTime;

public class Reminder<T extends Requestable> extends Request<T> {

    private LocalDateTime remindTime;

    public Reminder(Long id, LocalDateTime timeWhenDelivered, RequestType type, User sender, User recipient, T body, String template, LocalDateTime remindTime) {
        super(id, timeWhenDelivered, type, sender, recipient, body, template);
        this.remindTime = remindTime;
    }

    public LocalDateTime getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(LocalDateTime remindTime) {
        this.remindTime = remindTime;
    }
}
