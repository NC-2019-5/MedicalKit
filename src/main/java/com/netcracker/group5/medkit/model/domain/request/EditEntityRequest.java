package com.netcracker.group5.medkit.model.domain.request;

import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.user.User;

import java.time.LocalDateTime;
import java.util.Map;

public class EditEntityRequest<T extends Requestable> extends Request<T> {

    private Map<String, String> fields;

    public EditEntityRequest(Long id, LocalDateTime timeWhenDelivered, RequestType type, User sender, User recipient, T body, String template, Map<String, String> fields) {
        super(id, timeWhenDelivered, type, sender, recipient, body, template);
        this.fields = fields;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }
}
