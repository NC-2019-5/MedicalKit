package com.netcracker.group5.medkit.model.domain.request;

import com.netcracker.group5.medkit.model.domain.Requestable;

import java.time.LocalDateTime;
import java.util.Objects;

public class Reminder<T extends Requestable> extends Request<T> {

    private LocalDateTime remindTime;

    private Reminder() {
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

    @Override
    public String toString() {
        return "Reminder{" +
                "id=" + id +
                ", remindTime=" + remindTime +
                ", deliveredTime=" + deliveredTime +
                ", type=" + type +
                ", sender=" + sender +
                ", recipient=" + recipient +
                ", body=" + body +
                ", template='" + template + '\'' +
                '}';
    }

    public static <T extends Requestable> Reminder<T>.Builder newBuilder() {
        return new Reminder<T>().new Builder();
    }

    public class Builder extends AbstractBuilder<Builder> {

        private Builder() {
        }

        public Builder setRemindTime(LocalDateTime remindTime) {
            Reminder.this.remindTime = remindTime;
            return this;
        }

        public Reminder<T> build() {
            return Reminder.this;
        }
    }
}
