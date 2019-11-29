package com.netcracker.group5.medkit.model.domain.request;

import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.enumeration.RequestType;
import com.netcracker.group5.medkit.model.domain.user.User;

import java.util.Date;

public class Reminder<T extends Requestable> extends Request<T> {

    private Date remindTime;

    public Reminder(Long id, Date timeWhenDelivered, RequestType type, User sender, User recipient, T body, String template, Date remindTime) {
        super(id, timeWhenDelivered, type, sender, recipient, body, template);
        this.remindTime = remindTime;
    }

    public Date getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(Date remindTime) {
        this.remindTime = remindTime;
    }
}
