package com.netcracker.group5.medkit.model.domain.request;

import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.user.User;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reminder<?> reminder = (Reminder<?>) o;
        return remindTime.equals(reminder.remindTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remindTime);
    }
}
