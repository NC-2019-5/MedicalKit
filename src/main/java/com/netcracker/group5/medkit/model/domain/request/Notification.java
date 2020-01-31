package com.netcracker.group5.medkit.model.domain.request;

import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;
import com.netcracker.group5.medkit.model.domain.prescription.PrescriptionItem;
import com.netcracker.group5.medkit.model.dto.request.NotificationRequestItem;

import java.time.LocalDateTime;

public class Notification implements Requestable {

    private Long id;
    private Long userId;
    private NotificationType type;
    private LocalDateTime remindTime;
    private Long medicineInstanceId;
    private Long prescriptionItemId;
    private String message;

    public Notification() {
    }

    public Notification(NotificationRequestItem requestItem) {
        this.id = requestItem.getId();
        this.userId = requestItem.getId();
        this.type = requestItem.getType();
        this.remindTime = requestItem.getRemindTime();
        this.medicineInstanceId = requestItem.getMedicineInstanceId();
        this.prescriptionItemId = requestItem.getPrescriptionItemId();
        this.message = requestItem.getMessage();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public LocalDateTime getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(LocalDateTime remindTime) {
        this.remindTime = remindTime;
    }

    public Long getMedicineInstanceId() {
        return medicineInstanceId;
    }

    public void setMedicineInstanceId(Long medicineInstanceId) {
        this.medicineInstanceId = medicineInstanceId;
    }

    public Long getPrescriptionItemId() {
        return prescriptionItemId;
    }

    public void setPrescriptionItemId(Long prescriptionItemId) {
        this.prescriptionItemId = prescriptionItemId;
    }

    public static Builder newBuilder() {
        return new Notification().new Builder();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class Builder {

        public Builder() {
        }

        public Builder setId(Long id) {
            Notification.this.id = id;
            return this;
        }

        public Builder setUserId(Long userId) {
            Notification.this.userId = userId;
            return this;
        }

        public Builder setType(NotificationType type) {
            Notification.this.type = type;
            return this;
        }

        public Builder setRemindTime(LocalDateTime remindTime) {
            Notification.this.remindTime = remindTime;
            return this;
        }

        public Builder setMedicineInstance(Long medicineInstanceId) {
            Notification.this.medicineInstanceId = medicineInstanceId;
            return this;
        }

        public Builder setPrescriptionItem(Long purchaseItemId) {
            Notification.this.prescriptionItemId = purchaseItemId;
            return this;
        }

        public Builder setMessage(String message) {
            Notification.this.message = message;
            return this;
        }

        public Notification build() {
            return Notification.this;
        }
    }
}
