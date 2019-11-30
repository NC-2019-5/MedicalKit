package com.netcracker.group5.medkit.model.domain.request;

import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.user.User;

import java.time.LocalDateTime;
import java.util.Objects;

public class Request<T extends Requestable> {

    protected Long id;
    protected LocalDateTime deliveredTime;
    protected RequestType type;
    protected User sender;
    protected User recipient;
    protected T body;
    protected String template;

    public Request(Long id, LocalDateTime deliveredTime, RequestType type, User sender, User recipient, T body, String template) {
        this.id = id;
        this.deliveredTime = deliveredTime;
        this.type = type;
        this.sender = sender;
        this.recipient = recipient;
        this.body = body;
        this.template = template;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDeliveredTime() {
        return deliveredTime;
    }

    public void setDeliveredTime(LocalDateTime deliveredTime) {
        this.deliveredTime = deliveredTime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request<?> request = (Request<?>) o;
        return id.equals(request.id) &&
                Objects.equals(deliveredTime, request.deliveredTime) &&
                type == request.type &&
                Objects.equals(sender, request.sender) &&
                Objects.equals(recipient, request.recipient) &&
                Objects.equals(body, request.body) &&
                Objects.equals(template, request.template);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deliveredTime, type, sender, recipient, body, template);
    }
}
