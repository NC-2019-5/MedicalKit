package com.netcracker.group5.medkit.model.domain.request;

import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.enumeration.RequestType;
import com.netcracker.group5.medkit.model.domain.user.User;
import java.util.Date;

public class Request<T extends Requestable> {

    protected long id;
    protected Date timeWhenDelivered;
    protected RequestType type;
    protected User sender;
    protected User recipient;
    protected T body;
    protected String template;

    public Request(long id, Date timeWhenDelivered, RequestType type, User sender, User recipient, T body, String template) {
        this.id = id;
        this.timeWhenDelivered = timeWhenDelivered;
        this.type = type;
        this.sender = sender;
        this.recipient = recipient;
        this.body = body;
        this.template = template;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTimeWhenDelivered() {
        return timeWhenDelivered;
    }

    public void setTimeWhenDelivered(Date timeWhenDelivered) {
        this.timeWhenDelivered = timeWhenDelivered;
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
