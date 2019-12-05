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

    protected Request() {
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

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", deliveredTime=" + deliveredTime +
                ", type=" + type +
                ", sender=" + sender +
                ", recipient=" + recipient +
                ", body=" + body +
                ", template='" + template + '\'' +
                '}';
    }

    public static <T extends Requestable> Request<T>.Builder newRequestBuilder() {
        return new Request<T>().new Builder();
    }

    public class Builder extends AbstractBuilder<Builder> {

        private Builder() {
        }

        public Request<T> build() {
            return Request.this;
        }
    }

    public abstract class AbstractBuilder<B extends AbstractBuilder<B>> {

        protected AbstractBuilder() {
        }

        public B setId(Long id) {
            Request.this.id = id;
            return getThis();
        }

        public B setDeliveredTime(LocalDateTime deliveredTime) {
            Request.this.deliveredTime = deliveredTime;
            return getThis();
        }

        public B setType(RequestType type) {
            Request.this.type = type;
            return getThis();
        }

        public B setSender(User sender) {
            Request.this.sender = sender;
            return getThis();
        }

        public B setRecipient(User recipient) {
            Request.this.recipient = recipient;
            return getThis();
        }

        public B setBody(T body) {
            Request.this.body = body;
            return getThis();
        }

        public B setTemplate(String template) {
            Request.this.template = template;
            return getThis();
        }

        private B getThis() {
            return (B) this;
        }
    }
}
